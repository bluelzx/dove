package com.gustz.dove.cli.api.service;

import java.nio.charset.Charset;
import java.util.Map;

import com.gustz.dove.cli.api.service.vo.Attachment;
import com.gustz.dove.cli.api.service.vo.UploadFileForm;

/**
 * 
 * TODO: Async HTTP client service
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 9, 2015 ]
 */
public interface AsyncHttpCliService {

    /**
     * 
     * Get request
     * 
     * @param url
     * @return
     * @throws Exception
     */
    String get(String url) throws Exception;

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
    String get(Charset charset, String url, Map<String, String> headerMap) throws Exception;

    /**
     * 
     * Get request
     * 
     * @param url
     * @param paramMap
     * @return
     * @throws Exception
     */
    String get(String url, Map<String, String> paramMap) throws Exception;

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
    String get(Charset charset, String url, Map<String, String> paramMap, Map<String, String> headerMap) throws Exception;

    /**
     * Post request
     * 
     * @param url
     * @param param
     * @return
     * @throws Exception
     */
    String post(String url, String param) throws Exception;

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
    String post(Charset charset, String url, String param, Map<String, String> headerMap) throws Exception;

    /**
     * Post request
     * 
     * @param url
     * @param paramMap
     * @return
     * @throws Exception
     */
    String post(String url, Map<String, String> paramMap) throws Exception;

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
    String post(Charset charset, String url, Map<String, String> paramMap, Map<String, String> headerMap) throws Exception;

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
    String postSoapXml(String url, String tns, String methodName, String xml) throws Exception;

    /**
     * Upload file of post request
     * 
     * @param url
     * @param fileForms
     * @return
     * @throws Exception 
     */
    String uploadFile(String url, UploadFileForm[] fileForms) throws Exception;

    /**
     * Download file of get request
     * 
     * @param url
     * @return
     * @throws Exception 
     */
    Attachment downloadFile(String url) throws Exception;

}