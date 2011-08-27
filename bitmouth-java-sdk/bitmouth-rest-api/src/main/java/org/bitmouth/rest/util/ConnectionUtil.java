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

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;
import java.net.URL;

import org.bitmouth.rest.api.exceptions.AuthorizationException;
import org.bitmouth.rest.api.exceptions.BadGatewayException;
import org.bitmouth.rest.api.exceptions.BadRequestException;
import org.bitmouth.rest.api.exceptions.ConnectionException;
import org.bitmouth.rest.api.exceptions.NoContentException;
import org.bitmouth.rest.api.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Shamaila Tahir
 *
 */
public class ConnectionUtil {
    final static Logger logger = LoggerFactory.getLogger(ConnectionUtil.class);

    public static InputStream doGet(URL url, String resourceId){
	try {
	    logger.info("Opening connection to url: {} for a GET request.",url.toString());
	    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	    connection.setReadTimeout(5000);
	    connection.setRequestMethod("GET");	    	    	    
	    ConnectionUtil.processResponseCode(connection.getResponseCode(), resourceId,connection);
	    return connection.getInputStream();
	} catch (IOException e) {
	    throw new ConnectionException(
		    "Exception while connecting to server", e);
	}
    }
    
    public static InputStream doPost(URL url, String resourceId, String postContent){
	try {
	    logger.info("Opening connection to url: {} for a POST request. Payload {}",url.toString(),postContent);
	    HttpURLConnection connection = (HttpURLConnection) url
		    .openConnection();
	    connection.setReadTimeout(5000);
	    connection.setRequestMethod("POST");
	    connection.setDoOutput(true);
	    OutputStreamWriter out = new OutputStreamWriter(
		    connection.getOutputStream());
	    out.write(postContent);
	    out.close();
	    ConnectionUtil.processResponseCode(connection.getResponseCode(), resourceId,connection);
	    return connection.getInputStream();
	} catch(SocketTimeoutException e){
	    throw new RuntimeException(e);
	}catch (ProtocolException e) {
	    throw new RuntimeException(e);
	} catch (IOException e) {
	    throw new RuntimeException(e);
	}
    }
    /**
     * @param responseCode
     * @param resourceId TODO
     * @param inputStream 
     */
    public static void processResponseCode(int responseCode, String resourceId, HttpURLConnection connection) {
	logger.debug("HTTP Status={}",responseCode);
	RuntimeException exception = null;
	switch (responseCode) {
	case HttpURLConnection.HTTP_OK:		   
	    break;
	case HttpURLConnection.HTTP_NOT_FOUND:
	    exception = new ResourceNotFoundException(resourceId);
	    break;
	case HttpURLConnection.HTTP_NO_CONTENT:
	    exception = new NoContentException();
	    break;
	case HttpURLConnection.HTTP_BAD_GATEWAY:
	    exception = new BadGatewayException();
	    break;
	case HttpURLConnection.HTTP_BAD_REQUEST:
	    exception = new BadRequestException();
	    break;
	case HttpURLConnection.HTTP_UNAUTHORIZED:
	    exception = new AuthorizationException();
	    break;
	case HttpURLConnection.HTTP_INTERNAL_ERROR:
	    exception = new IllegalStateException();
	    break;
	default:
	    break;
	}
	if(exception !=null){
	    String response = JSONHelper.readInputStreamAsString(connection.getErrorStream());
	    logger.debug("Throwing exception, response was: {}", response);	    
	    throw exception;
	}
    }
}
