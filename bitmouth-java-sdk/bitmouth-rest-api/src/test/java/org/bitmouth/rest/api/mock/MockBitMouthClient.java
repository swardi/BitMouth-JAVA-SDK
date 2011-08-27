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

import java.util.HashMap;
import java.util.Map;

import org.bitmouth.rest.api.BitMouthClient;
import org.bitmouth.rest.api.ConferenceTemplate;
import org.bitmouth.rest.api.MediaTemplate;
import org.bitmouth.rest.api.NetworkIdsTemplate;
import org.bitmouth.rest.api.exceptions.BadGatewayException;
import org.bitmouth.rest.api.exceptions.BadRequestException;
import org.bitmouth.rest.api.exceptions.ResourceNotFoundException;
import org.bitmouth.rest.api.resources.ConferenceInfo;
import org.bitmouth.rest.api.resources.MediaInfo;
import org.bitmouth.rest.api.resources.NetworkIdInfo;
import org.bitmouth.rest.impl.resources.ConferenceInfoImpl;
import org.bitmouth.rest.impl.resources.MediaInfoImpl;

/**
 * @author Shamaila Tahir
 *
 */
public class MockBitMouthClient implements BitMouthClient {
    private String appKey;
    private Map<String,MediaInfoImpl> mediaMap = new HashMap<String,MediaInfoImpl>();    
    private Map<String, ConferenceInfoImpl> conferenceMap = new HashMap<String, ConferenceInfoImpl>();
    /**
     * @param appKey
     */
    public MockBitMouthClient(String appKey) {
	super();
	this.appKey = appKey;
    }

    /* (non-Javadoc)
     * @see org.bitmouth.rest.api.BitMouthClient#networkIds()
     */
    public NetworkIdsTemplate networkIds() {	
	return new MockNetworkIdsTemplate(appKey);
    }

    /* (non-Javadoc)
     * @see org.bitmouth.rest.api.BitMouthClient#createMedia(org.bitmouth.rest.api.resources.NetworkIdInfo)
     */
    public MediaInfo createMedia() throws BadRequestException, ResourceNotFoundException{
	MediaInfoImpl mediaInfo = new MediaInfoImpl();
	String hash = String.valueOf(new Object().hashCode());
	mediaInfo.setDownloadUrl("");
	mediaInfo.setFileSize(0);
	mediaInfo.setMediaId(hash);
	mediaInfo.setUri("api/rest/media/" + hash);
	mediaMap.put(hash,mediaInfo);
	return mediaInfo;
    }

    /* (non-Javadoc)
     * @see org.bitmouth.rest.api.BitMouthClient#media(org.bitmouth.rest.api.resources.MediaInfo)
     */
    public MediaTemplate media(MediaInfo mediaInfo) {	
	return new MockMediaTemplate(mediaMap.get(mediaInfo.getMediaId()));
    }

    /* (non-Javadoc)
     * @see org.bitmouth.rest.api.BitMouthClient#createConference(boolean, org.bitmouth.rest.api.resources.NetworkIdInfo[])
     */
    public ConferenceInfo createConference(boolean record,
	    NetworkIdInfo... networkIds) throws BadGatewayException {
	ConferenceInfoImpl conferenceInfoImpl = new ConferenceInfoImpl();
	String hash = String.valueOf(new Object().hashCode());
	conferenceInfoImpl.setConferenceId(hash);
	conferenceInfoImpl.setUri("api/rest/conference/"+ hash);	
	conferenceMap.put(hash,conferenceInfoImpl);
	return conferenceInfoImpl;
    }

    /* (non-Javadoc)
     * @see org.bitmouth.rest.api.BitMouthClient#conference(org.bitmouth.rest.api.resources.ConferenceInfo)
     */
    public ConferenceTemplate conference(ConferenceInfo conference) {	
	ConferenceInfoImpl conferenceInfoImpl = conferenceMap.get(conference.getUri());
	MockConferenceTemplate mockConferenceTemplate = new MockConferenceTemplate(conferenceInfoImpl);
	return mockConferenceTemplate;
    }

    /* (non-Javadoc)
     * @see org.bitmouth.rest.api.BitMouthClient#getAPIVersion()
     */
    public String getAPIVersion() {
	return "1.0";
    }

}
