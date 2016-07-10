package com.gustz.dove.cli.api.comm.service.impl;

import java.io.BufferedInputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import com.gustz.dove.cli.api.service.conf.AsyncHttpCliConf;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.AbstractHttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.http.impl.nio.conn.PoolingNHttpClientConnectionManager;
import org.apache.http.impl.nio.reactor.DefaultConnectingIOReactor;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.nio.reactor.ConnectingIOReactor;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinovatech.rd.wcsb.cli.api.service.AsyncHttpCliService;
import com.gustz.dove.cli.api.service.conf.BaseWcsbConf;
import com.sinovatech.rd.wcsb.cli.api.service.dict.RspCodeDict;
import com.sinovatech.rd.wcsb.cli.api.service.vo.Attachment;
import com.sinovatech.rd.wcsb.cli.api.service.vo.UploadFileForm;

/**
 * 
 * TODO: Async HTTP client service impl
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 9, 2015 ]
 */
@Service
public class AsyncHttpCliServiceImpl implements AsyncHttpCliService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private BaseWcsbConf baseWcsbConf;

    @Autowired
    private AsyncHttpCliConf asyncHttpCliConf;

    private CloseableHttpAsyncClient asyncHttpClient;

    private Charset charset;

    private RequestConfig requestConfig;

    private IdlePoolingNConnEvictor connEvictor;

    /**
     * Init config
     */
    @PostConstruct
    private void init() {
        try {
            // set config
            charset = Charset.forName(baseWcsbConf.getDefaultCharset());
            int connTimeoutMs = asyncHttpCliConf.getConnTimeoutMs();
            int soTimeoutMs = asyncHttpCliConf.getSoTimeoutMs();
            int maxTotal = asyncHttpCliConf.getMaxTotal();
            int maxPerRoute = asyncHttpCliConf.getMaxPerRoute();
            int connRequestTimeoutMs = asyncHttpCliConf.getConnRequestTimeoutMs();
            //
            // Create I/O reactor configuration
            IOReactorConfig ioReactorConfig = IOReactorConfig.custom() //
                    .setIoThreadCount(Runtime.getRuntime().availableProcessors()) //
                    .setConnectTimeout(connTimeoutMs) //
                    .setSoTimeout(soTimeoutMs) //
                    .build();

            // Create a custom I/O reactort
            ConnectingIOReactor ioReactor = new DefaultConnectingIOReactor(ioReactorConfig);

            // Create a connection manager with custom configuration.
            PoolingNHttpClientConnectionManager connMgr = new PoolingNHttpClientConnectionManager(ioReactor);
            connMgr.setMaxTotal(maxTotal); // max number of connections allowed
            connMgr.setDefaultMaxPerRoute(maxPerRoute); // max number of connections allowed per route
            //
            asyncHttpClient = HttpAsyncClientBuilder.create().setConnectionManager(connMgr).build();
            //
            requestConfig = RequestConfig.custom() //
                    .setSocketTimeout(soTimeoutMs) //
                    .setConnectTimeout(connTimeoutMs) //
                    .setConnectionRequestTimeout(connRequestTimeoutMs) //
                    .build();
            //
            connEvictor = new IdlePoolingNConnEvictor(connMgr);
            connEvictor.start();
        } catch (Exception e) {
            throw new Error("Init async HttpClient is fail. \n", e);
        }
    }

    @PreDestroy
    private void dostory() {
        try {
            if (connEvictor != null) {
                connEvictor.shutdown();
            }
            if (asyncHttpClient != null) {
                asyncHttpClient.close();
            }
        } catch (Exception e) {
            throw new Error("Dostory async HttpClient is fail. \n", e);
        }
    }

    /**
     * 
     * Get request
     * 
     * @param url
     * @return
     * @throws Exception
     */
    @Override
    public String get(String url) throws Exception {
        //
        return get(charset, url, null);
    }

    /**
     * 
     * Get request
     * 
     * @param charset
     * @param url
     * @param headerMap
     * @return
     * @throws Exception
     */
    @Override
    public String get(Charset charset, String url, Map<String, String> headerMap) throws Exception {
        HttpGet httpGet = new HttpGet(url);
        ContentTypeEnum contentType = null;
        if (headerMap != null && !headerMap.isEmpty()) {
            for (Map.Entry<String, String> _entry : headerMap.entrySet()) {
                httpGet.setHeader(_entry.getKey(), _entry.getValue());
            }
        } else {
            contentType = ContentTypeEnum.HTML;
        }
        return executeToStr(charset, httpGet, contentType);
    }

    /**
     * 
     * Get request
     * 
     * @param url
     * @param paramMap
     * @return
     * @throws Exception
     */
    @Override
    public String get(String url, Map<String, String> paramMap) throws Exception {
        return get(charset, url, paramMap, null);
    }

    /**
     * 
     * Get request
     * 
     * @param charset
     * @param url
     * @param paramMap
     * @param headerMap
     * @return
     * @throws Exception
     */
    @Override
    public String get(Charset charset, String url, Map<String, String> paramMap, Map<String, String> headerMap) throws Exception {
        if (paramMap != null && !paramMap.isEmpty()) {
            StringBuffer sbf = new StringBuffer(url);
            List<NameValuePair> ps = new ArrayList<NameValuePair>();
            for (Map.Entry<String, String> _entry : paramMap.entrySet()) {
                ps.add(new BasicNameValuePair(_entry.getKey(), _entry.getValue()));
            }
            sbf.append("?");
            sbf.append(URLEncodedUtils.format(ps, charset));
            url = sbf.toString();
        }
        //
        ContentTypeEnum contentType = null;
        HttpGet httpGet = new HttpGet(url);
        if (headerMap != null && !headerMap.isEmpty()) {
            for (Map.Entry<String, String> _entry : headerMap.entrySet()) {
                httpGet.setHeader(_entry.getKey(), _entry.getValue());
            }
        } else {
            contentType = ContentTypeEnum.HTML;
        }
        return executeToStr(charset, httpGet, contentType);
    }

    /**
     * Post request
     * 
     * @param url
     * @param param
     * @return
     * @throws Exception
     */
    @Override
    public String post(String url, String param) throws Exception {
        //
        return post(charset, url, param, null);
    }

    /**
     * Post request
     * 
     * @param charset
     * @param url
     * @param param
     * @param headerMap
     * @return
     * @throws Exception
     */
    @Override
    public String post(Charset charset, String url, String param, Map<String, String> headerMap) throws Exception {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(new StringEntity(param, charset));
        //
        ContentTypeEnum contentType = null;
        if (headerMap != null && !headerMap.isEmpty()) {
            for (Map.Entry<String, String> _entry : headerMap.entrySet()) {
                httpPost.setHeader(_entry.getKey(), _entry.getValue());
            }
        } else {
            contentType = ContentTypeEnum.HTML;
        }
        //
        return executeToStr(charset, httpPost, contentType);
    }

    /**
     * Post request
     * 
     * @param url
     * @param paramMap
     * @return
     * @throws Exception
     */
    @Override
    public String post(String url, Map<String, String> paramMap) throws Exception {
        //
        return post(charset, url, paramMap, null);
    }

    /**
     * Post request
     * 
     * @param charset
     * @param url
     * @param paramMap
     * @param headerMap
     * @return
     * @throws Exception
     */
    @Override
    public String post(Charset charset, String url, Map<String, String> paramMap, Map<String, String> headerMap) throws Exception {
        AbstractHttpEntity httpEntity = null;
        if (paramMap != null && !paramMap.isEmpty()) {
            List<NameValuePair> formParams = new ArrayList<NameValuePair>();
            for (Map.Entry<String, String> _entry : paramMap.entrySet()) {
                formParams.add(new BasicNameValuePair(_entry.getKey(), _entry.getValue()));
            }
            httpEntity = new UrlEncodedFormEntity(formParams, charset);
        }
        //
        HttpPost httpPost = new HttpPost(url);
        if (httpEntity != null) {
            httpPost.setEntity(httpEntity);
        }
        ContentTypeEnum contentType = null;
        if (headerMap != null && !headerMap.isEmpty()) {
            for (Map.Entry<String, String> _entry : headerMap.entrySet()) {
                httpPost.setHeader(_entry.getKey(), _entry.getValue());
            }
        } else {
            contentType = ContentTypeEnum.HTML;
        }
        //
        return executeToStr(charset, httpPost, contentType);
    }

    /**
     * Executing and to string
     * 
     * @param charset
     * @param requestBase
     * @param contentType
     * @return
     * @throws Exception
     */
    private String executeToStr(Charset charset, HttpRequestBase requestBase, ContentTypeEnum contentType) throws Exception {
        return EntityUtils.toString(this.execute(charset, requestBase, contentType).getEntity(), charset);
    }

    /**
     * Executing
     * 
     * @param charset
     * @param requestBase
     * @param contentType
     * @return
     * @throws Exception
     */
    private HttpResponse execute(Charset charset, HttpRequestBase requestBase, ContentTypeEnum contentType) throws Exception {
        HttpResponse response = null;
        try {
            requestBase.setConfig(requestConfig);
            this.setDyncHeader(charset, requestBase, contentType);
            //
            asyncHttpClient.start();
            logger.info("Executing request line: {}", requestBase.getRequestLine());
            //
            Future<HttpResponse> future = asyncHttpClient.execute(requestBase, null);
            //
            response = future.get();
            logger.info("Response status line: {}", response.getStatusLine());
            assertStatus(response);
            return response;
        } finally {
            requestBase.releaseConnection();
        }
    }

    /**
     * Post request for SOAP XML
     * 
     * @param url
     * @param tns
     * @param methodName
     * @param xml
     * @return
     * @throws Exception
     */
    @Override
    public String postSoapXml(String url, String tns, String methodName, String xml) throws Exception {
        // SOAP XML
        xml = getRequestSoapXml(tns, methodName, StringEscapeUtils.escapeXml10(xml));
        //
        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(new StringEntity(xml, charset));
        //
        String rspMsg = executeToStr(charset, httpPost, ContentTypeEnum.SOAP_XML);
        // format response message
        if (rspMsg != null && !rspMsg.isEmpty()) {
            if (rspMsg.indexOf("<return>") > 0) { // CXF
                rspMsg = rspMsg.substring(rspMsg.indexOf("<return>") + 8, rspMsg.lastIndexOf("</return>"));
            } else if (rspMsg.indexOf(":out>") > 0) { //XFIRE
                rspMsg = rspMsg.substring(rspMsg.indexOf(":out>") + 5, rspMsg.lastIndexOf(":out>") - 5);
            }
        }
        return StringEscapeUtils.unescapeXml(rspMsg);
    }

    /**
     * Upload file of post request
     * 
     * @param url
     * @param fileForms
     * @return
     * @throws Exception
     */
    @Override
    public String uploadFile(String url, UploadFileForm[] fileForms) throws Exception {
        CloseableHttpClient httpclient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost(url);
        try {
            httpPost.setHeader("connection", "Keep-Alive");
            // multi file
            MultipartEntityBuilder builder = MultipartEntityBuilder.create() //
                    .setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            //
            for (int i = 0; i < fileForms.length; i++) {
                UploadFileForm form = fileForms[i];
                // file
                builder.addBinaryBody(form.getFileKey(), form.getFileValue());
                // text
                Map<String, String> _map = form.getTextBody();
                if (_map == null || _map.isEmpty()) {
                    continue;
                }
                for (Map.Entry<String, String> _entry : _map.entrySet()) {
                    builder.addTextBody(_entry.getKey(), _entry.getValue(), ContentType.TEXT_PLAIN.withCharset(charset));
                }
            }
            httpPost.setEntity(builder.build());
            //
            HttpResponse rsp = httpclient.execute(httpPost);
            //
            return EntityUtils.toString(rsp.getEntity(), charset);
        } finally {
            httpPost.releaseConnection();
            httpclient.close();
        }
    }

    /**
     * Download file of get request
     * 
     * @param url
     * @return
     * @throws Exception 
     */
    @Override
    public Attachment downloadFile(String url) throws Exception {
        Attachment atta = new Attachment();
        HttpGet httpGet = new HttpGet(url);
        // do
        HttpResponse rsp = this.execute(charset, httpGet, ContentTypeEnum.HTML);
        HttpEntity httpEntity = rsp.getEntity();
        if (httpEntity.getContentType().getValue().equalsIgnoreCase("text/plain")) { // error file
            atta.setRspCode(RspCodeDict.A0006);
        } else { // ok
            String fullName = null;
            String relName = null;
            String suffix = null;
            Header dispHeader = rsp.getFirstHeader("Content-disposition");
            if (dispHeader != null) {
                String disp = dispHeader.getValue();
                //
                fullName = disp.substring(disp.indexOf("filename=\"") + 10, disp.length() - 1);
                relName = fullName.substring(0, fullName.lastIndexOf("."));
                suffix = fullName.substring(relName.length() + 1);
            }
            // set
            atta.setFullName(fullName);
            atta.setFileName(relName);
            atta.setSuffix(suffix);
            atta.setContentLength(httpEntity.getContentLength());
            atta.setContentType(httpEntity.getContentType().getValue());
            atta.setFileStream(new BufferedInputStream(httpEntity.getContent()));
        }
        return atta;
    }

    /**
     * Get request SOAP XML
     * 
     * @param tns
     * @param methodName
     * @param params
     * @return
     */
    private String getRequestSoapXml(String tns, String methodName, String... params) {
        StringBuilder sbd = new StringBuilder();
        sbd.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        sbd.append("<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" ");
        sbd.append(" xmlns:sam=\"").append(((tns != null && !tns.endsWith("/")) ? tns + "/" : tns)).append("\" "); // targetNamespace
        sbd.append(" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\"");
        sbd.append(" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">");
        sbd.append("<soap:Header/>");
        sbd.append("<soap:Body>");
        sbd.append("<sam:").append(methodName).append(">"); // method name
        // arguments begin
        for (int i = 0, len = params.length; i < len; i++) {
            sbd.append("<arg").append(i).append(">").append(params[i]).append("</arg").append(i).append(">");
        }
        // arguments end
        sbd.append("</sam:").append(methodName).append(">");
        sbd.append("</soap:Body>");
        sbd.append("</soap:Envelope>");
        return sbd.toString();
    }

    private void assertStatus(HttpResponse response) {
        if (response == null) {
            throw new IllegalArgumentException("HttpResponse is null.");
        }
        if (response.getStatusLine() == null) {
            throw new IllegalArgumentException("HttpResponse status is null.");
        }
        int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode != HttpStatus.SC_OK) {
            throw new IllegalStateException("Http response is fail.status code is " + statusCode);
        }
    }

    private void setDyncHeader(Charset charset, HttpRequestBase requestBase, ContentTypeEnum contentType) {
        requestBase.setHeader(HTTP.CONTENT_ENCODING, charset.toString());
        if (contentType != null) {
            requestBase.setHeader(HttpHeaders.ACCEPT, contentType.getValue());
            requestBase.setHeader(HTTP.CONTENT_TYPE, contentType.getValue() + ";charset=" + charset);
        }
    }

    private enum ContentTypeEnum {
        /**
         * SOAP XML content type
         */
        SOAP_XML("application/soap+xml"),

        HTML("text/html");

        private final String value;

        private ContentTypeEnum(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }
    }

    public static final class IdlePoolingNConnEvictor {

        private final ThreadFactory threadFactory;

        private final Thread thread;

        private final long sleepTimeMs;

        private final long maxIdleTimeMs;

        private volatile boolean shutdown;

        public IdlePoolingNConnEvictor(final PoolingNHttpClientConnectionManager connMgr, final ThreadFactory threadFactory,
                final long sleepTime, final TimeUnit sleepTimeUnit, final long maxIdleTime, final TimeUnit maxIdleTimeUnit) {
            if (connMgr == null) {
                throw new IllegalArgumentException("PoolingNHttpClient connection manager may not be null");
            }
            //
            this.threadFactory = threadFactory != null ? threadFactory : new DefaultThreadFactory();
            this.sleepTimeMs = sleepTimeUnit != null ? sleepTimeUnit.toMillis(sleepTime) : sleepTime;
            this.maxIdleTimeMs = maxIdleTimeUnit != null ? maxIdleTimeUnit.toMillis(maxIdleTime) : maxIdleTime;
            this.thread = this.threadFactory.newThread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (!shutdown) {
                            synchronized (this) {
                                wait(sleepTimeMs);
                                connMgr.closeExpiredConnections();
                                if (maxIdleTimeMs > 0) {
                                    connMgr.closeIdleConnections(maxIdleTimeMs, TimeUnit.MILLISECONDS);
                                }
                            }
                        }
                    } catch (Exception e) {
                        throw new Error("Connection evictor", e);
                    }
                }
            });
        }

        public IdlePoolingNConnEvictor(final PoolingNHttpClientConnectionManager connMgr, final long sleepTime,
                final TimeUnit sleepTimeUnit, final long maxIdleTime, final TimeUnit maxIdleTimeUnit) {
            this(connMgr, null, sleepTime, sleepTimeUnit, maxIdleTime, maxIdleTimeUnit);
        }

        public IdlePoolingNConnEvictor(final PoolingNHttpClientConnectionManager connMgr, final long maxIdleTime,
                final TimeUnit maxIdleTimeUnit) {
            this(connMgr, null, maxIdleTime > 0 ? maxIdleTime : 5, maxIdleTimeUnit != null ? maxIdleTimeUnit : TimeUnit.SECONDS,
                    maxIdleTime, maxIdleTimeUnit);
        }

        public IdlePoolingNConnEvictor(final PoolingNHttpClientConnectionManager connMgr) {
            this(connMgr, 5L, TimeUnit.SECONDS);
        }

        public void start() {
            thread.start();
        }

        public void shutdown() {
            shutdown = true;
            synchronized (this) {
                notifyAll();
            }
        }

        public boolean isRunning() {
            return thread.isAlive();
        }

        public void awaitTermination(final long time, final TimeUnit tunit) throws InterruptedException {
            thread.join((tunit != null ? tunit : TimeUnit.MILLISECONDS).toMillis(time));
        }

        static class DefaultThreadFactory implements ThreadFactory {

            @Override
            public Thread newThread(final Runnable r) {
                final Thread t = new Thread(r, "Connection evictor");
                t.setDaemon(true);
                return t;
            }
        };
    }

}