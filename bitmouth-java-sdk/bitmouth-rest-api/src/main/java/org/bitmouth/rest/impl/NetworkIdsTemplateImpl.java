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
package org.bitmouth.rest.impl;

import java.io.InputStream;
import java.net.URL;

import org.bitmouth.rest.api.NetworkIdsTemplate;
import org.bitmouth.rest.api.exceptions.AuthorizationException;
import org.bitmouth.rest.api.exceptions.BadGatewayException;
import org.bitmouth.rest.api.exceptions.BadRequestException;
import org.bitmouth.rest.api.exceptions.ResourceNotFoundException;
import org.bitmouth.rest.api.resources.NetworkIdInfo;
import org.bitmouth.rest.util.ConnectionUtil;
import org.bitmouth.rest.util.JSONHelper;
import org.bitmouth.rest.util.UrlBuilder;
import org.bitmouth.rest.util.UrlBuilder.URLBuilderFactory;

/**
 * @author Shamaila Tahir
 *
 */
public class NetworkIdsTemplateImpl implements NetworkIdsTemplate{

    private URLBuilderFactory urlBuilderFactory;
    
    public NetworkIdsTemplateImpl(URLBuilderFactory urlBuilderFactory) {
	this.urlBuilderFactory = urlBuilderFactory;
    }
    
    /* (non-Javadoc)
     * @see org.bitmouth.rest.api.NetworkIdsTemplate#isRegistered(java.lang.String)
     */
    public boolean isRegistered(String networkId) throws AuthorizationException {
	UrlBuilder urlBuilder = urlBuilderFactory.get();
	URL url = urlBuilder.addPathSegment("registrant")
		.addPathSegment(networkId)
		.addPostParameter("query", "exists")
		.create();
	
	InputStream inputStream;
	try {
	    inputStream = ConnectionUtil.doPost(url, networkId,
	    	urlBuilder.getPostContents());
	} catch (ResourceNotFoundException e) {
	    return false;
	}

	return true;
    }

    /* (non-Javadoc)
     * @see org.bitmouth.rest.api.NetworkIdsTemplate#getNetworkIdAssociatedWithApplication(java.lang.String)
     */
    public boolean getNetworkIdAssociatedWithApplication(String networkId)
	    throws AuthorizationException {
	UrlBuilder urlBuilder = urlBuilderFactory.get();
	URL url = urlBuilder.addPathSegment("registrant")
		.addPathSegment(networkId)
		.addPostParameter("query", "related")
		.create();
	
	try {
	    InputStream inputStream = ConnectionUtil.doPost(url, networkId,
			urlBuilder.getPostContents());
	} catch (ResourceNotFoundException e) {
	    return false;
	}

	return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.bitmouth.rest.api.NetworkIdsTemplate#register(java.lang.String,
     * java.lang.String)
     */
    public NetworkIdInfo register(String networkId, String phone)
	    throws BadRequestException, BadGatewayException {
	UrlBuilder urlBuilder = urlBuilderFactory.get();
	URL url = urlBuilder.addPathSegment("registrant")
	.addPostParameter("networkid", networkId)
	.addPostParameter("phone", phone)
	.create();
	InputStream inputStream = ConnectionUtil.doPost(url, networkId,
		urlBuilder.getPostContents());
	
	NetworkIdInfo networkIdinfo = JSONHelper.readNetworkIdInfoFromJSON(inputStream);
	if(networkIdinfo != null)
	    networkIdinfo.setNetworkId(networkId);
	return networkIdinfo;
    }

}
