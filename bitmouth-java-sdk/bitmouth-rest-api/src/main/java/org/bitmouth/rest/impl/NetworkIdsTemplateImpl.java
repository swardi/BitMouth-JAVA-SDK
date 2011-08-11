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

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.bitmouth.rest.api.NetworkIdsTemplate;
import org.bitmouth.rest.api.exceptions.AuthorizationException;
import org.bitmouth.rest.api.exceptions.BadGatewayException;
import org.bitmouth.rest.api.exceptions.BadRequestException;
import org.bitmouth.rest.api.exceptions.ConnectionException;
import org.bitmouth.rest.api.exceptions.NoContentException;
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
		.addParameter("query", "exists")
		.createForGet();
	InputStream inputStream = ConnectionUtil.doGet(url, networkId);
	NetworkIdInfo networkIdInfo = JSONHelper
		.readNetworkIdInfoFromJSON(inputStream);
	return networkIdInfo != null;
    }

    /* (non-Javadoc)
     * @see org.bitmouth.rest.api.NetworkIdsTemplate#getNetworkIdAssociatedWithApplication(java.lang.String)
     */
    public NetworkIdInfo getNetworkIdAssociatedWithApplication(String networkId)
	    throws ResourceNotFoundException, AuthorizationException {
	UrlBuilder urlBuilder = urlBuilderFactory.get();
	URL url = urlBuilder.addPathSegment("registrant")
		.addPathSegment(networkId)
		.addParameter("query", "related")
		.createForGet();
	InputStream inputStream = ConnectionUtil.doGet(url, networkId);
	NetworkIdInfo networkIdInfo = JSONHelper
		.readNetworkIdInfoFromJSON(inputStream);
	return networkIdInfo;
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
	.addParameter("networkid", networkId)
	.addParameter("phone", phone)
	.createForPost();
	InputStream inputStream = ConnectionUtil.doPost(url, networkId,
		urlBuilder.getPostContents());
	
	NetworkIdInfo networkIdinfo = JSONHelper.readNetworkIdInfoFromJSON(inputStream);
	if(networkIdinfo != null)
	    networkIdinfo.setNetworkId(networkId);
	return networkIdinfo;
    }

}
