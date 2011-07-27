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

import org.bitmouth.rest.api.MediaTemplate;
import org.bitmouth.rest.api.exceptions.BadGatewayException;
import org.bitmouth.rest.api.exceptions.BadRequestException;
import org.bitmouth.rest.api.exceptions.ResourceNotFoundException;
import org.bitmouth.rest.api.resources.MediaInfo;
import org.bitmouth.rest.api.resources.NetworkIdInfo;
import org.bitmouth.rest.api.resources.UploadInfo;
import org.bitmouth.rest.impl.resources.MediaInfoImpl;
import org.bitmouth.rest.impl.resources.UploadInfoImpl;

/**
 * @author Shamaila Tahir
 *
 */
public class MockMediaTemplate implements MediaTemplate {

    private MediaInfoImpl mediaInfo;

    /**
     * @param mediaInfo
     */
    public MockMediaTemplate(MediaInfoImpl mediaInfo) {
	this.mediaInfo = mediaInfo;
    }

    /* (non-Javadoc)
     * @see org.bitmouth.rest.api.MediaTemplate#record(java.lang.String)
     */
    public MediaInfo record(NetworkIdInfo networkid) throws BadRequestException,
	    ResourceNotFoundException, BadGatewayException {	
	return mediaInfo;
    }

    /* (non-Javadoc)
     * @see org.bitmouth.rest.api.MediaTemplate#blast(java.lang.String)
     */
    public MediaInfo blast(NetworkIdInfo networkid) throws BadRequestException,
	    ResourceNotFoundException, BadGatewayException {
	return mediaInfo;
    }

    /* (non-Javadoc)
     * @see org.bitmouth.rest.api.MediaTemplate#grantUpload()
     */
    public UploadInfo grantUpload() throws BadRequestException,
	    ResourceNotFoundException, BadGatewayException {
	UploadInfoImpl uploadInfoImpl = new UploadInfoImpl();
	String hash = String.valueOf(new Object().hashCode());
	uploadInfoImpl.setGrantId(hash);
	uploadInfoImpl.setUri("api/rest/media/" + mediaInfo.getMediaId() + "/" + hash);
	return uploadInfoImpl;
    }

    /* (non-Javadoc)
     * @see org.bitmouth.rest.api.MediaTemplate#removeMedia()
     */
    public MediaInfo removeMedia() throws BadRequestException,
	    ResourceNotFoundException, BadGatewayException {
	return mediaInfo;
    }

    /* (non-Javadoc)
     * @see org.bitmouth.rest.api.MediaTemplate#status()
     */
    public MediaInfo status() {
	return mediaInfo;
    }

}
