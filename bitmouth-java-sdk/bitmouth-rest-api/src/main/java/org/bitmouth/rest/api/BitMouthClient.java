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

import org.bitmouth.rest.api.exceptions.BadGatewayException;
import org.bitmouth.rest.api.exceptions.BadRequestException;
import org.bitmouth.rest.api.exceptions.ResourceNotFoundException;
import org.bitmouth.rest.api.resources.ConferenceInfo;
import org.bitmouth.rest.api.resources.MediaInfo;
import org.bitmouth.rest.api.resources.NetworkIdInfo;

/**
 * BitMouthClient represents an interface to BitMouth for a particular application. 
 * Each BitMouthClient instance is associated with a particular BitMouthApplication 
 * The client exposes methods for creating and manipulating different resources on BitMouth server
 * Instances of BitMouthClient can be created with {@link BitMouth#createClient(String, String)} factory method 
 * @see BitMouth#createClient(String, String)
 * @author Shamaila Tahir
 */
public interface BitMouthClient {

    /**
     * Returns a NetworkIdsTemplate that can be used to register and query status of networkId
     * @return
     */
    public NetworkIdsTemplate networkIds();

    /**
     * This method returns a Media ID that can be used with subsequent "record" and "blast" methods.
     * @return
     */
    public MediaInfo createMedia(NetworkIdInfo networkId) throws BadRequestException, ResourceNotFoundException;
    
    /**
     * This methods returns a MediaTemplate to work with a particular media id
     * @param mediaId
     * @return MediaTemplate for given media id
     */
    public MediaTemplate media(MediaInfo mediaId);    
    
    /**
     * This method creates a conference call with two or more participants.  
     * @param record Pass true if this conference be recorded
     * @param networkIds That should be initially part of this conference
     * @return
     */
    public ConferenceInfo createConference(boolean record, NetworkIdInfo... networkIds) throws BadGatewayException;
    
    /**
     * Get a ConferenceTemplate for this conference
     * @param conference
     * @return
     */
    public ConferenceTemplate conference(ConferenceInfo conference);
    
    public String getAPIVersion();
}