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

import org.bitmouth.rest.api.MediaTemplate;
import org.bitmouth.rest.api.exceptions.BadGatewayException;
import org.bitmouth.rest.api.exceptions.BadRequestException;
import org.bitmouth.rest.api.exceptions.ResourceNotFoundException;
import org.bitmouth.rest.api.resources.MediaInfo;
import org.bitmouth.rest.api.resources.NetworkIdInfo;
import org.bitmouth.rest.api.resources.UploadInfo;
import org.bitmouth.rest.util.ConnectionUtil;
import org.bitmouth.rest.util.JSONHelper;
import org.bitmouth.rest.util.UrlBuilder;
import org.bitmouth.rest.util.UrlBuilder.URLBuilderFactory;

/**
 * @author Shamaila Tahir
 * 
 */
public class MediaTemplateImpl implements MediaTemplate {

    private MediaInfo mediaInfo;
    private URLBuilderFactory urlBuilderFactory;

    /**
     * @param mediaInfo
     * @param urlBuilderFactory
     */
    public MediaTemplateImpl(MediaInfo mediaInfo, URLBuilderFactory urlBuilderFactory) {
	super();
	this.mediaInfo = mediaInfo;
	this.urlBuilderFactory = urlBuilderFactory;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.bitmouth.rest.api.MediaTemplate#record(java.lang.String)
     */
    public MediaInfo record(NetworkIdInfo networkInfo)
	    throws BadRequestException, ResourceNotFoundException,
	    BadGatewayException {
	UrlBuilder urlBuilder = urlBuilderFactory.get();
	urlBuilder.addPathSegment("media")
		  .addPathSegment(mediaInfo.getMediaId())
		  .addPostParameter("networkid", networkInfo.getNetworkId())
		  .addPostParameter("action", "record");
	URL url = urlBuilder.create();
	String resourceId = networkInfo.getNetworkId();
	InputStream is = ConnectionUtil.doPost(url, resourceId,urlBuilder.getPostContents());
	return JSONHelper.readMediaInfoFromJSON(is);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.bitmouth.rest.api.MediaTemplate#blast(java.lang.String)
     */
    public MediaInfo blast(NetworkIdInfo networkInfo) throws BadRequestException,
	    ResourceNotFoundException, BadGatewayException {
	UrlBuilder urlBuilder = urlBuilderFactory.get().addPathSegment("media")
	  		.addPathSegment(mediaInfo.getMediaId())
	  		.addPostParameter("networkid", networkInfo.getNetworkId())
	  		.addPostParameter("action", "blast");
	URL url = urlBuilder.create();
	InputStream is = ConnectionUtil.doPost(url, networkInfo.getNetworkId(),urlBuilder.getPostContents());
	//TODO are you sure we want to replace class level mediaInfo with this newly created mediaInfo. see in grantUpload also
	return mediaInfo = JSONHelper.readMediaInfoFromJSON(is);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.bitmouth.rest.api.MediaTemplate#grantUpload()
     */
    public UploadInfo grantUpload() throws BadRequestException,
	    ResourceNotFoundException, BadGatewayException {
	UrlBuilder urlBuilder = urlBuilderFactory.get().addPathSegment("media")
		.addPathSegment(mediaInfo.getMediaId())
		.addPostParameter("action", "upload_grant");
        URL url = urlBuilder.create();
        InputStream is = ConnectionUtil.doPost(url,mediaInfo.getMediaId(),urlBuilder.getPostContents());
        return JSONHelper.readUploadInfoFromJSON(is);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.bitmouth.rest.api.MediaTemplate#removeMedia()
     */
    public MediaInfo removeMedia() throws BadRequestException,
	    ResourceNotFoundException, BadGatewayException {
	UrlBuilder urlBuilder = urlBuilderFactory.get().addPathSegment("media")
	.addPathSegment(mediaInfo.getMediaId())
	.addPostParameter("action", "remove");
	URL url = urlBuilder.create();
	InputStream is = ConnectionUtil.doPost(url, mediaInfo.getMediaId(),urlBuilder.getPostContents());
	return mediaInfo = JSONHelper.readMediaInfoFromJSON(is);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.bitmouth.rest.api.MediaTemplate#status()
     */
    public MediaInfo status() {
	UrlBuilder urlBuilder = urlBuilderFactory.get().addPathSegment("media")
								.addPathSegment("status")
								.addPathSegment(mediaInfo.getMediaId());
	URL url = urlBuilder.create();
	InputStream is = ConnectionUtil.doGet(url, mediaInfo.getMediaId());
	return JSONHelper.readMediaInfoFromJSON(is);

    }

}
