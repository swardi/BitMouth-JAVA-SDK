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

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bitmouth.rest.api.NetworkIdsTemplate;
import org.bitmouth.rest.api.exceptions.AuthorizationException;
import org.bitmouth.rest.api.exceptions.BadGatewayException;
import org.bitmouth.rest.api.exceptions.BadRequestException;
import org.bitmouth.rest.api.exceptions.ResourceNotFoundException;
import org.bitmouth.rest.api.resources.NetworkIdInfo;
import org.bitmouth.rest.impl.resources.NetworkIdInfoImpl;
import org.bitmouth.rest.util.JSONHelper;
import org.bitmouth.rest.util.Util;

/**
 * @author Shamaila Tahir
 *
 */
public class MockNetworkIdsTemplate implements NetworkIdsTemplate {
    
    private List<String> myappNetworkIds = new ArrayList<String>(){{
	add("123");
    }};
    
    private Map<String,List<String>> associatedNetworkIds =  new HashMap<String, List<String>>(){{
	put("myapp",myappNetworkIds);
    }};
    
    private String appKey;
    
    /**
     * @param appKey
     */
    public MockNetworkIdsTemplate(String appKey) {
	super();
	this.appKey = appKey;
    }

    /* (non-Javadoc)
     * @see org.bitmouth.rest.api.NetworkIdsTemplate#isRegistered(java.lang.String)
     */
    public boolean isRegistered(String networkId) throws AuthorizationException {
	InputStream jsonStream = getClass().getResourceAsStream("networkid/"+networkId + ".json.txt");	
	NetworkIdInfo networkIdInfo = jsonStream==null?null:JSONHelper.readNetworkIdInfoFromJSON(jsonStream);
	return networkIdInfo!=null;
    }

    /* (non-Javadoc)
     * @see org.bitmouth.rest.api.NetworkIdsTemplate#getNetworkIdAssociatedWithApplication(java.lang.String)
     */
    public NetworkIdInfo getNetworkIdAssociatedWithApplication(String networkId)
	    throws ResourceNotFoundException, AuthorizationException {
	InputStream jsonStream = getClass().getResourceAsStream("networkid/"+ networkId + ".json.txt");
	if( jsonStream == null){
	    throw new ResourceNotFoundException(networkId);
	}	
	List<String> appNetworkIds = associatedNetworkIds.get(appKey);
	if(!appNetworkIds.contains(networkId) ) {
	    throw new AuthorizationException();
	}	
	return JSONHelper.readNetworkIdInfoFromJSON(jsonStream);
    }

    /* (non-Javadoc)
     * @see org.bitmouth.rest.api.NetworkIdsTemplate#register(java.lang.String, java.lang.String)
     */
    public NetworkIdInfo register(String networkId, String phone)
	    throws BadRequestException, BadGatewayException {
	if(Util.isEmpty(networkId) || Util.isEmpty(phone)) {
	    throw new BadRequestException();
	}
	NetworkIdInfoImpl networkIdInfoImpl = new NetworkIdInfoImpl();
	networkIdInfoImpl.setUri("/api/networkid/"+networkId);	
	return networkIdInfoImpl;
    }

}
