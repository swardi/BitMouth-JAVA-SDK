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
package org.bitmouth.rest.api.mock;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.bitmouth.rest.api.resources.*;
import org.bitmouth.rest.impl.resources.*;

import com.google.gson.Gson;

/**
 * 
 * @author Shamaila Tahir
 */
public class JSONHelper {
    
    public static ConferenceInfo readConferenceInfoFromJSON(String json){
	return readJSONFromString(json, ConferenceInfoImpl.class);
    }

    public static ConferenceInfo readConferenceInfoFromJSON(InputStream json){
	return readJSONFromString(readInputStreamAsString(json), ConferenceInfoImpl.class);
    }

    public static MediaInfo readMediaInfoFromJSON(String json){
	return readJSONFromString(json, MediaInfoImpl.class);
    }

    public static MediaInfo readMediaInfoFromJSON(InputStream json){
	return readJSONFromString(readInputStreamAsString(json), MediaInfoImpl.class);
    }
    
    public static NetworkIdInfo readNetworkIdInfoFromJSON(String json){
	return readJSONFromString(json, NetworkIdInfoImpl.class);
    }

    public static NetworkIdInfo readNetworkIdInfoFromJSON(InputStream json){
	return readJSONFromString(readInputStreamAsString(json), NetworkIdInfoImpl.class);
    }

    public static RegistrationInfo readRegistrationInfoFromJSON(String json){
	return readJSONFromString(json, RegistrationInfoImpl.class);
    }

    public static RegistrationInfo readRegistrationInfoFromJSON(InputStream json){
	return readJSONFromString(readInputStreamAsString(json), RegistrationInfoImpl.class);
    }

    public static UploadInfo readUploadInfoFromJSON(String json){
	return readJSONFromString(json, UploadInfoImpl.class);
    }

    public static UploadInfo readUploadInfoFromJSON(InputStream json){
	return readJSONFromString(readInputStreamAsString(json), UploadInfoImpl.class);
    }

    public static <T> T readJSONFromString(String json, Class<T> clazz){
	Gson gson = new Gson();
	T object=gson.fromJson(json, clazz);
	return object;
    }
 
    public static String readInputStreamAsString(InputStream in) {
	try {
	    BufferedInputStream bis = new BufferedInputStream(in);
	    ByteArrayOutputStream buf = new ByteArrayOutputStream();
	    int result = bis.read();
	    while (result != -1) {
		byte b = (byte) result;
		buf.write(b);
		result = bis.read();
	    }
	    return buf.toString();
	} catch (IOException e) {
	    throw new RuntimeException("Failed to read input stream ", e);
	}
    }  
}
