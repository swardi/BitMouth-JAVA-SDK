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
package org.bitmouth.rest.impl.resources;

import org.bitmouth.rest.api.resources.UploadInfo;

/**
 * @author Shamaila Tahir
 *
 */
public class UploadInfoImpl implements UploadInfo {

    private String grantId;
    private String uri;

    /* (non-Javadoc)
     * @see org.bitmouth.rest.api.resources.UploadInfo#getGrantId()
     */
    public String getGrantId() {
	return grantId;
    }

    /* (non-Javadoc)
     * @see org.bitmouth.rest.api.resources.UploadInfo#getUri()
     */
    public String getUri() {
	return uri;
    }

    /**
     * @param grantId the grantId to set
     */
    public void setGrantId(String grantId) {
        this.grantId = grantId;
    }

    /**
     * @param uri the uri to set
     */
    public void setUri(String uri) {
        this.uri = uri;
    }

}
