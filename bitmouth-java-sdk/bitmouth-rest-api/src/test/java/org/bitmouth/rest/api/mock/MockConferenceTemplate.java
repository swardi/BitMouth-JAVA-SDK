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

import org.bitmouth.rest.api.ConferenceTemplate;
import org.bitmouth.rest.api.exceptions.AuthorizationException;
import org.bitmouth.rest.api.exceptions.BadGatewayException;
import org.bitmouth.rest.api.exceptions.BadRequestException;
import org.bitmouth.rest.api.exceptions.ResourceNotFoundException;
import org.bitmouth.rest.api.resources.ConferenceInfo;
import org.bitmouth.rest.api.resources.NetworkIdInfo;
import org.bitmouth.rest.impl.resources.ConferenceInfoImpl;

/**
 * @author Shamaila Tahir
 *
 */
public class MockConferenceTemplate implements ConferenceTemplate {

    private ConferenceInfoImpl conferenceInfoImpl;

    /**
     * @param conferenceInfoImpl
     */
    public MockConferenceTemplate(ConferenceInfoImpl conferenceInfoImpl) {
	this.conferenceInfoImpl = conferenceInfoImpl;
    }

    /* (non-Javadoc)
     * @see org.bitmouth.rest.api.ConferenceTemplate#addToConference(org.bitmouth.rest.api.resources.NetworkIdInfo[])
     */
    public void addToConference(NetworkIdInfo... networkIdInfos)
	    throws BadRequestException, BadGatewayException {
	// TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see org.bitmouth.rest.api.ConferenceTemplate#closeConference()
     */
    public void closeConference() throws BadRequestException,
	    BadGatewayException, ResourceNotFoundException {
	// TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see org.bitmouth.rest.api.ConferenceTemplate#hangup(org.bitmouth.rest.api.resources.NetworkIdInfo)
     */
    public void hangup(NetworkIdInfo networkId) throws BadRequestException,
	    BadGatewayException, ResourceNotFoundException,
	    AuthorizationException {
	// TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see org.bitmouth.rest.api.ConferenceTemplate#move(org.bitmouth.rest.api.resources.ConferenceInfo, org.bitmouth.rest.api.resources.NetworkIdInfo)
     */
    public void move(ConferenceInfo toConference, NetworkIdInfo networkId) {
	// TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see org.bitmouth.rest.api.ConferenceTemplate#mute(org.bitmouth.rest.api.resources.NetworkIdInfo)
     */
    public void mute(NetworkIdInfo networkId) {
	// TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see org.bitmouth.rest.api.ConferenceTemplate#unmute(org.bitmouth.rest.api.resources.NetworkIdInfo)
     */
    public void unmute(NetworkIdInfo networkId) {
	// TODO Auto-generated method stub

    }

}
