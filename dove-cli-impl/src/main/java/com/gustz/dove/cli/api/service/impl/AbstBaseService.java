package com.gustz.dove.cli.api.service.impl;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.gustz.dove.cli.api.app.service.impl.ClientAppService;
import com.gustz.dove.cli.api.app.vo.ClientAppVo;
import com.sinovatech.rd.wcsb.cli.api.service.AsyncHttpCliService;
import com.sinovatech.rd.wcsb.cli.api.service.BaseService;
import com.sinovatech.rd.wcsb.cli.api.service.BaseWebsUrl;
import com.sinovatech.rd.wcsb.cli.api.service.CacheService;
import com.gustz.dove.cli.api.service.conf.WebsUrlParam;
import com.sinovatech.rd.wcsb.cli.api.service.dict.RspCodeDict;
import com.gustz.dove.cli.api.service.util.AppLogStyle;
import com.gustz.dove.cli.api.service.util.JsonMapper;
import com.sinovatech.rd.wcsb.cli.api.service.vo.AbstBaseReq;
import com.sinovatech.rd.wcsb.cli.api.service.vo.AbstBaseRsp;
import com.sinovatech.rd.wcsb.cli.api.service.vo.Attachment;
import com.sinovatech.rd.wcsb.cli.api.service.vo.ErrorBodyRsp;
import com.sinovatech.rd.wcsb.cli.api.service.vo.UploadFileForm;

/**
 * TODO: Base client service impl
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ]
 */
public abstract class AbstBaseService<REQ extends Serializable> implements BaseService<REQ> {

    private String dfPatt = "yyyy-MM-dd";

    private static final String FAIL_RSP = "fail";

    private Map<String, String> websUrlParamMap;

    private String accessToken;

    @Autowired
    private AsyncHttpCliService asyncHttpCliService;

    @Autowired
    private CacheService<String, Object> cacheService;

    @Autowired
    private ClientAppService clientAppService;

    /**
     * Is check client APP
     */
    private boolean isCheckCliApp = true;

    /**
     * try again number 2
     */
    private AtomicInteger tryAgainNum = new AtomicInteger(2);

    public void setDfPatt(String dfPatt) {
        this.dfPatt = dfPatt;
    }

    public void setWebsUrlParamMap(Map<String, String> websUrlParamMap) {
        this.websUrlParamMap = websUrlParamMap;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public void setCheckCliApp(boolean isCheckCliApp) {
        this.isCheckCliApp = isCheckCliApp;
    }

    /**
     * Check client APP
     * 
     * @param websUrl
     * @param cliAppCode
     * @param reqVo
     * @throws Exception 
     */
    private void checkCliApp(BaseWebsUrl websUrl, final String cliAppCode, final REQ reqVo) throws Exception {
        if (!this.isCheckCliApp) {
            // is check TODO
            return;
        }
        ClientAppVo vo = new ClientAppVo();
        // 客户端APP编码 1.
        vo.setCliAppCode(cliAppCode);

        // 客户端APP密码 2.
        final String cliAppPwd = this.readBeanVal(reqVo, AbstBaseReq.CLI_APP_PWD_FIELD);
        vo.setCliAppPwd(cliAppPwd);

        // 开发者账号 3.
        final String accountCode = this.readBeanVal(reqVo, AbstBaseReq.DEV_AC_CODE_FIELD);
        vo.setAccountCode(accountCode);

        // 服务编码（请求服务的接口编码）4.
        vo.setWebsCodes(websUrl.getWebsCode());

        // 客户端IP地址集 5.
        final String[] cliIpAddrs = this.readBeanVal(reqVo, AbstBaseReq.CLI_IP_ADDRS_FIELD);
        vo.setCliIpAddrsIn(cliIpAddrs);
        //
        this.clientAppService.checkCliApp(vo);
    }

    /**
     * Post for upload file
     * 
     * @param websUrl
     * @param sn
     * @param cliAppCode client appCode
     * @param reqVo
     * @param rspVo 
     * @return
     */
    @Override
    public <T> T uploadFile(BaseWebsUrl websUrl, long sn, String cliAppCode, final REQ reqVo, final T rspVo) {
        AppLogStyle appLogs = new AppLogStyle(sn + "", cliAppCode);
        T _rspVo = null;
        try {
            this.beginLog(appLogs, websUrl, reqVo, rspVo);
            // check client APP 1.
            this.checkCliApp(websUrl, cliAppCode, reqVo);
            // do 2.
            _rspVo = this.getUploadRspVo(appLogs, websUrl, reqVo, rspVo);
        } catch (Exception e) {
            _rspVo = this.getFailRspVo(rspVo);
            appLogs.error("", e);
        } finally {
            appLogs.end(this.toText(_rspVo));
        }
        return _rspVo;
    }

    /**
     * Get for download file
     * 
     * @param websUrl
     * @param sn
     * @param cliAppCode client appCode
     * @param reqVo
     * @param rspVo 
     * @return
     */
    @Override
    public <T> T downloadFile(BaseWebsUrl websUrl, long sn, String cliAppCode, final REQ reqVo, final T rspVo) {
        AppLogStyle appLogs = new AppLogStyle(sn + "", cliAppCode);
        T _rspVo = null;
        try {
            this.beginLog(appLogs, websUrl, reqVo, rspVo);
            // check client APP 1.
            this.checkCliApp(websUrl, cliAppCode, reqVo);
            // do 2.
            _rspVo = this.getDownloadRspVo(appLogs, websUrl, rspVo);
        } catch (Exception e) {
            _rspVo = this.getFailRspVo(rspVo);
            appLogs.error("", e);
        } finally {
            appLogs.end(this.toText(_rspVo));
        }
        return _rspVo;
    }

    /**
     * HTTP post request
     * 
     * @param websUrl
     * @param cliAppCode client appCode
     * @param reqVo
     * @param rspVo
     * @return
     */
    @Override
    public <T> T httpPost(BaseWebsUrl websUrl, String cliAppCode, final REQ reqVo, final T rspVo) {
        return httpPost(websUrl, System.currentTimeMillis(), cliAppCode, reqVo, rspVo);
    }

    /**
     * HTTP post request
     * 
     * @param websUrl
     * @param sn
     * @param cliAppCode client appCode
     * @param reqVo
     * @param rspVo
     * @return
     */
    @Override
    public <T> T httpPost(BaseWebsUrl websUrl, long sn, String cliAppCode, final REQ reqVo, final T rspVo) {
        AppLogStyle appLogs = new AppLogStyle(sn + "", cliAppCode);
        T _rspVo = null;
        try {
            this.beginLog(appLogs, websUrl, reqVo, rspVo);
            // check client APP 1.
            this.checkCliApp(websUrl, cliAppCode, reqVo);
            // do 2.
            _rspVo = this.getPostRspVo(appLogs, websUrl, reqVo, rspVo);
        } catch (Exception e) {
            _rspVo = this.getFailRspVo(rspVo);
            appLogs.error("", e);
        } finally {
            appLogs.end(this.toText(_rspVo));
        }
        return _rspVo;
    }

    /**
     * HTTP post request
     * 
     * @param websUrl
     * @param sn
     * @param cliAppCode
     * @param content
     * @return
     */
    @Override
    public String httpPost(String websUrl, long sn, String cliAppCode, final String content) {
        AppLogStyle appLogs = new AppLogStyle(sn + "", cliAppCode);
        String _retVal = FAIL_RSP;
        try {
            this.beginLog(appLogs, websUrl, content);
            // check client APP 1. TODO
            // this.checkCliApp(websUrl, cliAppCode, reqVo);
            // do 2.
            _retVal = asyncHttpCliService.post(websUrl, content);
        } catch (Exception e) {
            appLogs.error("", e);
        } finally {
            appLogs.end(_retVal);
        }
        return _retVal;
    }

    /**
     * HTTP get request
     * 
     * @param websUrl
     * @param cliAppCode client appCode
     * @param rspVo
     * @return
     */
    @Override
    public <T> T httpGet(BaseWebsUrl websUrl, String cliAppCode, final T rspVo) {
        return httpGet(websUrl, System.currentTimeMillis(), cliAppCode, rspVo);
    }

    /**
     * HTTP get request
     * 
     * @param websUrl
     * @param sn
     * @param cliAppCode client appCode
     * @param rspVo
     * @return 
     */
    @Override
    public <T> T httpGet(BaseWebsUrl websUrl, long sn, String cliAppCode, final T rspVo) {
        AppLogStyle appLogs = new AppLogStyle(sn + "", cliAppCode);
        T _rspVo = null;
        try {
            this.beginLog(appLogs, websUrl, null, rspVo);
            // check client APP 1. TODO
            // this.checkCliApp(websUrl, cliAppCode, reqVo);
            // do 2.
            _rspVo = this.getGetRspVo(appLogs, websUrl, rspVo);
        } catch (Exception e) {
            _rspVo = this.getFailRspVo(rspVo);
            appLogs.error("", e);
        } finally {
            appLogs.end(this.toText(_rspVo));
        }
        return _rspVo;
    }

    /**
     * Is try again
     * 
     * @param appLogs
     * @param rspVo 
     * @return
     */
    private <T> boolean isTryAgain(AppLogStyle appLogs, T rspVo) {
        Object _retVal = null;
        try {
            _retVal = this.readBeanVal(rspVo, AbstBaseRsp.RSP_CODE_FIELD);
        } catch (Exception e) {
            _retVal = null;
            appLogs.error("", e);
        }
        //
        return (_retVal != null && RspCodeDict.J9998X.getName().equals(_retVal + ""));
    }

    /**
     * Print begin log
     * 
     * @param appLogs
     * @param url
     * @param content
     */
    private <T> void beginLog(AppLogStyle appLogs, String url, String content) {
        appLogs.begin(String.format("%1$s,URL[ %2$s ].", content, url));
    }

    /**
     * Print begin log
     * 
     * @param appLogs
     * @param websUrl
     * @param reqVo
     * @param rspVo
     */
    private <T> void beginLog(AppLogStyle appLogs, final BaseWebsUrl websUrl, REQ reqVo, T rspVo) {
        appLogs.begin(String.format("%1$s,URL[ %2$s ]=:%3$s,rspVo=:%4$s .", this.toText(reqVo), websUrl.getText(),
                websUrl.getUrl(), rspVo));
    }

    /**
     * Write bean to JSON string
     * 
     * @param bean
     * @return
     * @throws JsonProcessingException
     */
    private String writeValueAsString(Object bean) throws JsonProcessingException {
        JsonMapper.setDateFormat(this.dfPatt);
        return JsonMapper.writeValueAsString(true, bean);
    }

    /**
     * Read JSON to bean
     * 
     * @param json
     * @param typeCls
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    private Object readValue(String json, Class<?> typeCls) throws JsonParseException, JsonMappingException, IOException {
        JsonMapper.setDateFormat(this.dfPatt);
        return JsonMapper.getObjectMapper().readValue(json, typeCls);
    }

    private String toText(Object msg) {
        return ReflectionToStringBuilder.toString(msg);
    }

    /**
     * Get get response VO
     * 
     * @param appLogs
     * @param websUrl
     * @param rspVo
     * @return
     * @throws Exception 
     */
    private <T> T getGetRspVo(AppLogStyle appLogs, final BaseWebsUrl websUrl, final T rspVo) throws Exception {
        T _retVo = null;
        try {
            String _url = this.fmtReqWebsUrl(websUrl);
            // log
            appLogs.runtime(_url);
            String json = fmtJSON(asyncHttpCliService.get(_url));
            // JSON to VO
            _retVo = this.getSetRspBodyByJson(appLogs, rspVo, json);
        } catch (Exception e) {
            throw e;
        } finally {
            // try again
            if (this.isTryAgain(appLogs, _retVo) && tryAgainNum.getAndDecrement() > 0) {
                appLogs.runtimeWarn(String.format("try again get request number is %1$s .", tryAgainNum.get()));
                try {
                    Thread.sleep(3000); // 3s
                    //
                    this.getGetRspVo(appLogs, websUrl, rspVo);
                } catch (InterruptedException e) {
                    appLogs.error("", e);
                    throw e;
                }
            }
        }
        return _retVo;
    }

    /**
     * Get post response VO
     * 
     * @param appLogs
     * @param websUrl
     * @param reqVo
     * @param rspVo
     * @return
     * @throws Exception 
     */
    private <T> T getPostRspVo(AppLogStyle appLogs, final BaseWebsUrl websUrl, final REQ reqVo, final T rspVo) throws Exception {
        T _retVo = null;
        try {
            String _url = this.fmtReqWebsUrl(websUrl);
            T _reqBodyVo = this.readBeanVal(reqVo, AbstBaseReq.BODY_FIELD); // get request body JSON
            // to JSON 
            String _param = this.writeValueAsString(_reqBodyVo);
            // log
            appLogs.runtime(_url + _param);
            String json = fmtJSON(asyncHttpCliService.post(_url, _param));
            // JSON to VO
            _retVo = this.getSetRspBodyByJson(appLogs, rspVo, json);
        } catch (Exception e) {
            throw e;
        } finally {
            // try again
            if (this.isTryAgain(appLogs, _retVo) && tryAgainNum.getAndDecrement() > 0) {
                appLogs.runtimeWarn(String.format("try again post request number is %1$s .", tryAgainNum.get()));
                try {
                    Thread.sleep(3000); // 3s
                    //
                    this.getPostRspVo(appLogs, websUrl, reqVo, rspVo);
                } catch (InterruptedException e) {
                    appLogs.error("", e);
                    throw e;
                }
            }
        }
        return _retVo;
    }

    /**
     * Get upload file response VO
     * 
     * @param appLogs
     * @param websUrl
     * @param reqVo
     * @param rspVo
     * @return
     * @throws Exception 
     */
    private <T> T getUploadRspVo(AppLogStyle appLogs, final BaseWebsUrl websUrl, final REQ reqVo, final T rspVo) throws Exception {
        T _retVo = null;
        try {
            String _url = this.fmtReqWebsUrl(websUrl);
            // get request body
            UploadFileForm[] _reqBodyVo = this.readBeanVal(reqVo, AbstBaseReq.BODY_FIELD);
            // print log
            appLogs.runtime(_url + _reqBodyVo);
            //
            String json = fmtJSON(asyncHttpCliService.uploadFile(_url, _reqBodyVo));
            // JSON to VO
            _retVo = this.getSetRspBodyByJson(appLogs, rspVo, json);
        } catch (Exception e) {
            throw e;
        }
        return _retVo;
    }

    /**
     * Get download file response VO
     * 
     * @param appLogs
     * @param websUrl
     * @param rspVo
     * @return
     * @throws Exception 
     */
    private <T> T getDownloadRspVo(AppLogStyle appLogs, final BaseWebsUrl websUrl, final T rspVo) throws Exception {
        T _retVo = null;
        try {
            String _url = this.fmtReqWebsUrl(websUrl);
            // print log
            appLogs.runtime(_url);
            //
            Attachment _atta = asyncHttpCliService.downloadFile(_url);
            _retVo = this.getSetRspBody(rspVo, _atta);
        } catch (Exception e) {
            throw e;
        }
        return _retVo;
    }

    private static String fmtJSON(String json) {
        if (json == null || json.isEmpty()) {
            json = "{}";
        }
        return json;
    }

    /**
     * Format web service URL
     * 
     * @param websUrl
     * @return
     */
    private String fmtReqWebsUrl(final BaseWebsUrl websUrl) {
        String _url = websUrl.getUrl();
        // replace URL param
        if (websUrlParamMap == null) {
            websUrlParamMap = new HashMap<String, String>();
        }
        // put access token
        websUrlParamMap.put(WebsUrlParam.ACCESS_TOKEN, this.accessToken);
        for (Map.Entry<String, String> _entry : this.websUrlParamMap.entrySet()) {
            String _key = _entry.getKey();
            String _val = _entry.getValue();
            if (StringUtils.isNotBlank(_key) && StringUtils.isNotBlank(_val)) {
                _url = _url.replace(_key, _val);
            }
        }
        return _url;
    }

    /**
     * Get fail response VO
     * 
     * @param rspVo
     * @return
     */
    private <T> T getFailRspVo(T rspVo) {
        try {
            this.setRspCode(rspVo, RspCodeDict.J9999);
        } catch (Exception e) {
            throw new Error(e);
        }
        return rspVo;
    }

    /**
     * Filter response VO
     * 
     * @param appLogs
     * @param rspVo
     * @param json
     * @return
     * @throws Exception 
     */
    @SuppressWarnings("unchecked")
    private <T> T filterRspVo(AppLogStyle appLogs, T rspVo, final String json) throws Exception {
        if (StringUtils.isBlank(json) || (json.indexOf("]") < 1 ? json.indexOf("}") < 1 ? true : false : false)) {
            rspVo = this.getFailRspVo(rspVo);
            appLogs.runtimeWarn("JSON string is illegal,response fail vo. \n");
        }
        if (!json.contains(ErrorBodyRsp.EXT_ERR_CODE_KEY)) {
            return rspVo;
        }
        // exist error
        Map<String, Object> _map = JsonMapper.getObjectMapper().readValue(json, HashMap.class);
        String _code = _map.get(ErrorBodyRsp.EXT_ERR_CODE_KEY) + "";
        if (!ErrorBodyRsp.OK_CODE.equals(_code)) {
            // rspCode
            this.setRspCode(rspVo, _code, _map.get(ErrorBodyRsp.EXT_ERR_MSG_KEY) + "");
            //
            appLogs.runtimeWarn(String.format("This is 3rd system response error message. %1$s \n", _map));
        }
        return rspVo;
    }

    /**
     * Get set response body data
     * 
     * @param appLogs
     * @param rspVo
     * @param json
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    private <T> T getSetRspBodyByJson(AppLogStyle appLogs, T rspVo, final String json) throws Exception {
        // filter response VO
        rspVo = this.filterRspVo(appLogs, rspVo, json);
        // read body VO
        T _bodyVo = this.readBeanVal(rspVo, AbstBaseReq.BODY_FIELD);
        // set body data
        _bodyVo = (T) this.readValue(json, _bodyVo.getClass());
        //
        return this.getSetRspBody(rspVo, _bodyVo);
    }

    /**
     * Set response code and msg
     * 
     * @param rspVo
     * @param codeDict
     * @throws Exception
     */
    private void setRspCode(Object rspVo, RspCodeDict codeDict) throws Exception {
        this.setRspCode(rspVo, codeDict.getName(), codeDict.getValue());
    }

    /**
     * Set response code and msg
     * 
     * @param rspVo
     * @param code
     * @param text
     * @throws Exception
     */
    private void setRspCode(Object rspVo, String code, String text) throws Exception {
        if (rspVo != null && StringUtils.isNotBlank(code)) {
            this.writeBeanVal(rspVo, AbstBaseRsp.RSP_CODE_FIELD, code);
            this.writeBeanVal(rspVo, AbstBaseRsp.RSP_MSG_FIELD, text);
        }
    }

    /**
     * Get set response body data
     * 
     * @param rspVo
     * @param value
     * @return
     * @throws Exception
     */
    private <T> T getSetRspBody(final T rspVo, Object value) throws Exception {
        //
        this.writeBeanVal(rspVo, AbstBaseReq.BODY_FIELD, value);
        // set success flag
        Object _retVal = this.readBeanVal(rspVo, AbstBaseRsp.RSP_CODE_FIELD);
        if (_retVal == null) {
            this.setRspCode(rspVo, RspCodeDict.A0000);
        }
        return rspVo;
    }

    /**
     * Read bean value
     * 
     * @param bean
     * @param fieldName
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    private <T> T readBeanVal(Object bean, String fieldName) throws Exception {
        if (bean == null || fieldName == null || fieldName.isEmpty()) {
            return null;
        }
        PropertyDescriptor[] descriptors = Introspector.getBeanInfo(bean.getClass()).getPropertyDescriptors();
        for (PropertyDescriptor desc : descriptors) {
            if (desc.getName().equals(fieldName)) {
                return (T) desc.getReadMethod().invoke(bean);
            }
        }
        return null;
    }

    /**
     * Write bean value
     * 
     * @param bean
     * @param fieldName
     * @param value
     * @return
     * @throws Exception
     */
    private void writeBeanVal(Object bean, String fieldName, Object value) throws Exception {
        if (bean == null || fieldName == null || fieldName.isEmpty()) {
            throw new IllegalArgumentException("Args 'bean/fieldName' is null.");
        }
        PropertyDescriptor[] descriptors = Introspector.getBeanInfo(bean.getClass()).getPropertyDescriptors();
        for (PropertyDescriptor desc : descriptors) {
            if (desc.getName().equals(fieldName)) {
                desc.getWriteMethod().invoke(bean, value);
            }
        }
    }

    /**
     * Create cache
     * 
     * @return
     */
    @Override
    public CacheService<String, Object> createCache() {
        return cacheService;
    }

    protected abstract void setAccessTokenX(long sn, String cliAppCode, String devAcCode);

}
