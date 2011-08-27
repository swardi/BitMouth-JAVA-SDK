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

import java.net.URL;

import org.bitmouth.rest.api.ConferenceTemplate;
import org.bitmouth.rest.api.exceptions.AuthorizationException;
import org.bitmouth.rest.api.exceptions.BadGatewayException;
import org.bitmouth.rest.api.exceptions.BadRequestException;
import org.bitmouth.rest.api.exceptions.ResourceNotFoundException;
import org.bitmouth.rest.api.resources.ConferenceInfo;
import org.bitmouth.rest.api.resources.NetworkIdInfo;
import org.bitmouth.rest.util.ConnectionUtil;
import org.bitmouth.rest.util.UrlBuilder;
import org.bitmouth.rest.util.UrlBuilder.URLBuilderFactory;

/**
 * @author Shamaila Tahir
 * 
 */
public class ConferenceTemplateImpl implements ConferenceTemplate {
    private URLBuilderFactory urlBuilderFactory;
    private ConferenceInfo conference;

    /**
     * @param urlBuilderFactory
     * @param conference
     */
    public ConferenceTemplateImpl(URLBuilderFactory urlBuilderFactory,
	    ConferenceInfo conference) {
	super();
	this.urlBuilderFactory = urlBuilderFactory;
	this.conference = conference;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.bitmouth.rest.api.ConferenceTemplate#addToConference(org.bitmouth
     * .rest.api.resources.NetworkIdInfo[])
     */
    public void addToConference(NetworkIdInfo... networkIdInfos)
	    throws BadRequestException, BadGatewayException {
	UrlBuilder urlBuilder = urlBuilderFactory.get()
		.addPathSegment("conference")
		.addPathSegment(conference.getConferenceId())
		.addPostParameter("action","add");
	for (NetworkIdInfo networkId : networkIdInfos) {
	    urlBuilder.addPostParameter("networkid", networkId.getNetworkId());
	}
	URL url = urlBuilder.create();
	ConnectionUtil.doPost(url, conference.getConferenceId(),
		urlBuilder.getPostContents());

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.bitmouth.rest.api.ConferenceTemplate#closeConference()
     */
    public void closeConference() throws BadRequestException,
	    BadGatewayException, ResourceNotFoundException {
	UrlBuilder urlBuilder = urlBuilderFactory.get()
		.addPathSegment("conference")
		.addPathSegment(conference.getConferenceId())
		.addPostParameter("action","close");
	URL url = urlBuilder.create();
	ConnectionUtil.doPost(url, conference.getConferenceId(),
		urlBuilder.getPostContents());

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.bitmouth.rest.api.ConferenceTemplate#hangup(org.bitmouth.rest.api
     * .resources.NetworkIdInfo)
     */
    public void hangup(NetworkIdInfo networkInfo) throws BadRequestException,
	    BadGatewayException, ResourceNotFoundException,
	    AuthorizationException {
	UrlBuilder urlBuilder = urlBuilderFactory.get()
		.addPathSegment("conference")
		.addPathSegment(conference.getConferenceId())
		.addPathSegment(networkInfo.getNetworkId())
		.addPostParameter("action","hangup");
	URL url = urlBuilder.create();
	ConnectionUtil.doPost(url, conference.getConferenceId(),
		urlBuilder.getPostContents());

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.bitmouth.rest.api.ConferenceTemplate#move(org.bitmouth.rest.api.resources
     * .ConferenceInfo, org.bitmouth.rest.api.resources.NetworkIdInfo)
     */
    public void move(ConferenceInfo toConference, NetworkIdInfo networkInfo) {
	UrlBuilder urlBuilder = urlBuilderFactory.get()
		.addPathSegment("conference")
		.addPathSegment(conference.getConferenceId())
		.addPathSegment(networkInfo.getNetworkId())
		.addPostParameter("toconferenceid",toConference.getConferenceId())
		.addPostParameter("action","move");
	URL url = urlBuilder.create();
	ConnectionUtil.doPost(url, conference.getConferenceId(),
		urlBuilder.getPostContents());

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.bitmouth.rest.api.ConferenceTemplate#mute(org.bitmouth.rest.api.resources
     * .NetworkIdInfo)
     */
    public void mute(NetworkIdInfo networkInfo) {
	doMute(true, networkInfo);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.bitmouth.rest.api.ConferenceTemplate#unmute(org.bitmouth.rest.api
     * .resources.NetworkIdInfo)
     */
    public void unmute(NetworkIdInfo networkId) {
	doMute(false, networkId);

    }

    public void doMute(boolean mute, NetworkIdInfo networkInfo) {
	UrlBuilder urlBuilder = urlBuilderFactory.get()
		.addPathSegment("conference")
		.addPathSegment(conference.getConferenceId())
		.addPathSegment(networkInfo.getNetworkId());
		if(mute)
		    urlBuilder.addPostParameter("action", "mute");
		else 
		    urlBuilder.addPostParameter("action", "unmute");
		
	URL url = urlBuilder.create();
	ConnectionUtil.doPost(url, conference.getConferenceId(),
		urlBuilder.getPostContents());
    }

}
