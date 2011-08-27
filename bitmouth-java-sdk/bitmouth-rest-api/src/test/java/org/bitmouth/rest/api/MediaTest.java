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
package org.bitmouth.rest.api;

import junit.framework.TestCase;

import org.bitmouth.rest.api.resources.MediaInfo;
import org.bitmouth.rest.api.resources.NetworkIdInfo;
import org.bitmouth.rest.api.resources.UploadInfo;
import org.bitmouth.rest.impl.MediaTemplateImpl;
import org.bitmouth.rest.impl.resources.MediaInfoImpl;

/**
 * @author Shamaila Tahir
 * 
 */
public class MediaTest extends BaseBitmouthTestCase {

    public void testCompleteLifeCycleOfMedia() {
	testCreateMedia();
	testRecord();
	testGrantUpload();
	testBlast();
	testStatus();
	testRemoveMedia();
	testStatus();

    }

    public void testCreateMedia() {
	MediaInfo mediaInfo;
	mediaInfo = client.createMedia();
	System.out.println("Media created with id=" + mediaInfo.getMediaId());
	assertNotNull(mediaInfo.getMediaId());
    }

    public void testRecord() {
	MediaInfo mediaInfo;
	mediaInfo = client.createMedia();
	System.out.println("Media created with id=" + mediaInfo.getMediaId());
	MediaTemplateImpl mediaTemplate = new MediaTemplateImpl(mediaInfo, urlBuilder);
	NetworkIdInfo newNetworkInfo = client.networkIds().register("11112121222", "34634634");
	assertNotNull(mediaTemplate.record(newNetworkInfo));

    }

    public void testGrantUpload() {

    }

    public void testBlast() {

    }

    public void testRemoveMedia() {

	MediaInfo mediaInfo;
	mediaInfo = client.createMedia();
	System.out.println("Media created with id=" + mediaInfo.getMediaId());
	MediaTemplateImpl mediaTemplate = new MediaTemplateImpl(mediaInfo, urlBuilder);
	assertNotNull(mediaTemplate.removeMedia());
    }

    public void testStatus() {
	MediaInfo mediaInfo;
	mediaInfo = client.createMedia();
	System.out.println("Media created with id=" + mediaInfo.getMediaId());
	MediaTemplateImpl mediaTemplate = new MediaTemplateImpl(mediaInfo, urlBuilder);
	assertNotNull(mediaTemplate.status());

    }

}
