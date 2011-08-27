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

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.Map.Entry;
import java.util.Set;

import org.bitmouth.rest.api.resources.*;
import org.bitmouth.rest.impl.resources.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

/**
 * 
 * @author Shamaila Tahir
 */
public class JSONHelper {    
    final static Logger logger = LoggerFactory.getLogger(JSONHelper.class);
     
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
	return readJSONFromString(readInputStreamAsString(json),  NetworkIdInfoImpl.class);
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
	logger.debug("Parsing JSON {} into class {}",json,clazz.getName());
	GsonBuilder gsonBuilder = new GsonBuilder();
	gsonBuilder.registerTypeAdapter(ResponseContainer.class, new ResponseDeserializer());	
	Gson gson = gsonBuilder.create();
	ResponseContainer response = gson.fromJson(json, ResponseContainer.class);
	logger.debug("Found {} in reponse object",response.getResponseName());
	T object=gson.fromJson(response.getResponseObject(), clazz);
	return object;
    }
 
    public static String readInputStreamAsString(InputStream in) {
	if(in == null ) return ""; // if server has not returned anything in the error stream.
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
    
    private static class ResponseContainer{
	private String responseName;
	private JsonObject responseObject;
	/**
	 * @param responseName
	 * @param responseObject
	 */
	public ResponseContainer(String responseName, JsonObject responseObject) {
	    super();
	    this.responseName = responseName;
	    this.responseObject = responseObject;
	}
	/**
	 * @return the responseName
	 */
	public String getResponseName() {
	    return responseName;
	}
	/**
	 * @return the responseObject
	 */
	public JsonObject getResponseObject() {
	    return responseObject;
	}
	
    }
    
    private static class ResponseDeserializer implements JsonDeserializer<ResponseContainer> {

	/* (non-Javadoc)
	 * @see com.google.gson.JsonDeserializer#deserialize(com.google.gson.JsonElement, java.lang.reflect.Type, com.google.gson.JsonDeserializationContext)
	 */
	public ResponseContainer deserialize(JsonElement json, Type typeOfT,
		JsonDeserializationContext context) throws JsonParseException {
	    JsonObject response = json.getAsJsonObject();
	    Set<Entry<String, JsonElement>> entries = response.entrySet();
	    if(entries.size() != 1){
		throw new RuntimeException("Expecting only one item in response, found " + entries.size());
	    }
	    Entry<String, JsonElement> entry = entries.iterator().next();
	    return new ResponseContainer(entry.getKey(), entry.getValue().getAsJsonObject());
	}	  
    }
}
