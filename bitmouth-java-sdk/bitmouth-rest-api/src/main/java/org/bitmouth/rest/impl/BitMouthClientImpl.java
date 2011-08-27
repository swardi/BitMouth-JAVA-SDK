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

import org.bitmouth.rest.api.BitMouthClient;
import org.bitmouth.rest.api.ConferenceTemplate;
import org.bitmouth.rest.api.MediaTemplate;
import org.bitmouth.rest.api.NetworkIdsTemplate;
import org.bitmouth.rest.api.resources.ConferenceInfo;
import org.bitmouth.rest.api.resources.MediaInfo;
import org.bitmouth.rest.api.resources.NetworkIdInfo;
import org.bitmouth.rest.util.ConnectionUtil;
import org.bitmouth.rest.util.JSONHelper;
import org.bitmouth.rest.util.UrlBuilder;
import org.bitmouth.rest.util.UrlBuilder.URLBuilderFactory;

/**
 * @author Shamaila Tahir
 *
 */
public class BitMouthClientImpl implements BitMouthClient {

    private URLBuilderFactory urlBuilderFactory;

    /**
     * @param urlBuilderFactory
     */
    public BitMouthClientImpl(URLBuilderFactory urlBuilderFactory) {
	this.urlBuilderFactory = urlBuilderFactory;
    }

    /* (non-Javadoc)
     * @see org.bitmouth.rest.api.BitMouthClient#networkIds()
     */
    public NetworkIdsTemplate networkIds() {
	return new NetworkIdsTemplateImpl(urlBuilderFactory);
    }

    /* (non-Javadoc)
     * @see org.bitmouth.rest.api.BitMouthClient#createMedia(org.bitmouth.rest.api.plumbing.NetworkIdInfo)
     */
    public MediaInfo createMedia() {
	UrlBuilder urlBuilder = urlBuilderFactory.get();
	URL url = urlBuilder.addPathSegment("media").create();
        InputStream inputStream = ConnectionUtil.doPost(url, "", urlBuilder.getPostContents());
	MediaInfo mediaInfo=null;
	mediaInfo=JSONHelper.readMediaInfoFromJSON(inputStream);
	return mediaInfo;
    }

    /* (non-Javadoc)
     * @see org.bitmouth.rest.api.BitMouthClient#media(org.bitmouth.rest.api.plumbing.MediaInfo)
     */
    public MediaTemplate media(MediaInfo mediaId) {
	return new MediaTemplateImpl(mediaId, urlBuilderFactory);
    }

    /* (non-Javadoc)
     * @see org.bitmouth.rest.api.BitMouthClient#createConference(boolean, org.bitmouth.rest.api.plumbing.NetworkIdInfo[])
     */
    public ConferenceInfo createConference(boolean record,
	    NetworkIdInfo... networkIds) {
	UrlBuilder urlBuilder = urlBuilderFactory.get()
				.addPathSegment("conference")
				.addPostParameter("record ", record);
	
	for(NetworkIdInfo networkId : networkIds){
	    urlBuilder.addPostParameter("networkid", networkId.getNetworkId());
	}
	URL url = urlBuilder.create();
        ConnectionUtil.doPost(url, "Conference info", urlBuilder.getPostContents());
        ConferenceInfo conferenceInfo=null;
	return conferenceInfo;
    }

    /* (non-Javadoc)
     * @see org.bitmouth.rest.api.BitMouthClient#conference(org.bitmouth.rest.api.plumbing.ConferenceInfo)
     */
    public ConferenceTemplate conference(ConferenceInfo conference) {
	return new ConferenceTemplateImpl(urlBuilderFactory, conference);
    }

    /* (non-Javadoc)
     * @see org.bitmouth.rest.api.BitMouthClient#getAPIVersion()
     */
    public String getAPIVersion() {
	UrlBuilder urlBuilder = urlBuilderFactory.get().addPathSegment("version");
	URL url = urlBuilder.create();
	InputStream inputStream = ConnectionUtil.doGet(url,"Version Info");
	
	return JSONHelper.readInputStreamAsString(inputStream);
    }
    
    
        
}
