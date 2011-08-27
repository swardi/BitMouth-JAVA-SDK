/**
 * Copyright (c) 2011 Telesocial, Inc.
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.bitmouth.rest.util;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Shamaila Tahir
 * 
 */
public class UrlBuilder {
    final   Logger logger = LoggerFactory.getLogger(UrlBuilder.class);
    private static final String QUERY_STRING_SEPARATOR = "?";
    private static final String AMPERSAND = "&";
    private static final String EQUALTO = "=";
    private static final String CONTENT_ENCODING = "UTF-8";
    private static final String PATH_SEGMENT_SEPARATOR = "/";

    private String baseUrl;
    private Map<String, List<String>> queryStringParameters;
    private Map<String, List<String>> postParameters;
    private List<String> pathSegments;
    private String encoding;
    
    public static class URLBuilderFactory{
	private String baseUrl;
	private String appKey;
	
	/**
	 * @param baseUrl
	 * @param appKey
	 */
	public URLBuilderFactory(String baseUrl, String appKey) {
	    super();
	    this.baseUrl = baseUrl;
	    this.appKey = appKey;
	}

	public UrlBuilder get(){
	    UrlBuilder urlBuilder = new UrlBuilder(baseUrl);
//	    urlBuilder.addGetParameter("appkey", appKey);
	    urlBuilder.addPostParameter("appkey", appKey);
	    return urlBuilder;	    
	}
    }
    
    public UrlBuilder(String url, String encoding) {
	if (url == null || url.trim().length() == 0) {
	    throw new IllegalArgumentException("Url cannot be empty");
	}
	// Remove trailing slash from the path
	if(url.lastIndexOf("/") == url.length()-1) 
	    url=url.substring(0,url.length()-1);
	this.encoding = encoding;
	this.queryStringParameters = new HashMap<String, List<String>>();
	this.postParameters = new HashMap<String, List<String>>();
	if (url == null || url.trim().length() == 0) {
	    throw new IllegalArgumentException("Path cannot be empty");
	}
	this.baseUrl = url;
	pathSegments = new ArrayList<String>();
    }

    public UrlBuilder(String url) {
	this(url, CONTENT_ENCODING);
    }

    public UrlBuilder addPathSegment(String pathSegment) {
	if (pathSegment == null || pathSegment.trim().length() == 0) {
	    throw new IllegalArgumentException(
		    "Path segment name cannot be empty");
	}
	pathSegments.add(pathSegment);
	return this;
    }

    /**
     * Add a string URL parameter value.
     * 
     * @param name
     * @param value
     * @return Current instance for builder pattern.
     */
    private UrlBuilder addParameter(String name, String value, Map<String, List<String>> parameters) {
	if (name == null || name.trim().length() == 0) {
	    throw new IllegalArgumentException("Parameter name cannot be empty");
	}
	List<String> values = parameters.get(name);
	if(values == null){
	    values = new ArrayList<String>();
	    parameters.put(name.trim(), values);
	}
	values.add(value);
	
	return this;
    }

    public UrlBuilder addPostParameter(String name, String value){
	return addParameter(name, value, postParameters);
    }
    
    public UrlBuilder addGetParameter(String name, String value){
	return addParameter(name, value, queryStringParameters);
    }
    
    
    /**
     * Add an integer URL parameter value.
     * 
     * @param name
     * @param value
     * @return Current instance for builder pattern.
     */
    public UrlBuilder addPostParameter(String name, int value ) {
	this.addParameter(name, Integer.toString(value), postParameters);
	return this;
    }

    
    /**
     * Add an integer URL parameter value.
     * 
     * @param name
     * @param value
     * @return Current instance for builder pattern.
     */
    public UrlBuilder addPostParameter(String name, boolean value ) {
	this.addParameter(name, Boolean.toString(value), postParameters);
	return this;
    }
    
    public UrlBuilder addGetParameter(String name, boolean value ) {
	this.addParameter(name, Boolean.toString(value),queryStringParameters);
	return this;
    }
    
    protected String buildUrl() {
	StringBuilder urlBuilder = new StringBuilder();
	buildBaseUrlWithPathSegments(urlBuilder);
	appendQueryString(urlBuilder);
	return urlBuilder.toString();
    }

    /**
     * @param urlBuilder
     */
    private void buildBaseUrlWithPathSegments(StringBuilder urlBuilder) {
	urlBuilder.append(this.baseUrl);
	addPathSegments(urlBuilder);
    }
   
    private void appendQueryString(StringBuilder urlBuilder){
	appendParameters(urlBuilder, QUERY_STRING_SEPARATOR, AMPERSAND, queryStringParameters);
    }
       


    /**
     * @param queryStringSeparator TODO
     * @param parameterPairSeparator TODO
     * @param parameters TODO
     * 
     */
    private void appendParameters(StringBuilder urlBuilder, String queryStringSeparator, String parameterPairSeparator, Map<String, List<String>> parameters) {
	if (parameters.size() > 0) {
	    urlBuilder.append(queryStringSeparator);
	    
	    boolean isFirstParameter=true;
	    for (Map.Entry<String, List<String>> entry : parameters.entrySet()) {
		
		for(String value : entry.getValue()){
		    if(!isFirstParameter) urlBuilder.append(parameterPairSeparator);
		    isFirstParameter=false;
		    urlBuilder.append(encodeUrl(entry.getKey()));
		    urlBuilder.append(EQUALTO);
		    urlBuilder.append(encodeUrl(value));
		}
		
	    }
	}
    }

    /**
     * 
     */
    private void addPathSegments(StringBuilder urlBuilder) {
	for (String pathSegment : pathSegments) {
	    urlBuilder.append(PATH_SEGMENT_SEPARATOR);
	    urlBuilder.append(pathSegment);
	}
    }

    /**
     * Encode URL content.
     * 
     * @param content
     *            Content to encode.
     * @return Encoded string.
     */
    protected static String encodeUrl(String content) {
	try {
	    return URLEncoder.encode(content, CONTENT_ENCODING);
	} catch (UnsupportedEncodingException e) {
	    // should never be here..
	    return content;
	}
    }
    
    public URL create(){
	URL url;
	try {
	    String urlString = buildUrl();
	    url = new URL(urlString);
	    logger.info("URL object created successfully for GET request, url = {}",urlString);
	} catch (MalformedURLException e) {
	    throw new IllegalStateException("API URL is malformed", e);
	}
	return url;
    }
        
    public String getPostContents() {
	StringBuilder urlBuilder = new StringBuilder();	
	appendParameters(urlBuilder, "", AMPERSAND, postParameters);
	return urlBuilder.toString();
    }
}
