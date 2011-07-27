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


import org.bitmouth.rest.api.BitMouthClient;
import org.bitmouth.rest.api.ConferenceTemplate;
import org.bitmouth.rest.api.MediaTemplate;
import org.bitmouth.rest.api.NetworkIdsTemplate;
import org.bitmouth.rest.api.resources.ConferenceInfo;
import org.bitmouth.rest.api.resources.MediaInfo;
import org.bitmouth.rest.api.resources.NetworkIdInfo;

/**
 * @author Shamaila Tahir
 *
 */
public class BitMouthClientImpl implements BitMouthClient {

    /* (non-Javadoc)
     * @see org.bitmouth.rest.api.BitMouthClient#networkIds()
     */
    public NetworkIdsTemplate networkIds() {
	// TODO Auto-generated method stub
	return null;
    }

    /* (non-Javadoc)
     * @see org.bitmouth.rest.api.BitMouthClient#createMedia(org.bitmouth.rest.api.plumbing.NetworkIdInfo)
     */
    public MediaInfo createMedia(NetworkIdInfo networkId) {
	// TODO Auto-generated method stub
	return null;
    }

    /* (non-Javadoc)
     * @see org.bitmouth.rest.api.BitMouthClient#media(org.bitmouth.rest.api.plumbing.MediaInfo)
     */
    public MediaTemplate media(MediaInfo mediaId) {
	// TODO Auto-generated method stub
	return null;
    }

    /* (non-Javadoc)
     * @see org.bitmouth.rest.api.BitMouthClient#createConference(boolean, org.bitmouth.rest.api.plumbing.NetworkIdInfo[])
     */
    public ConferenceInfo createConference(boolean record,
	    NetworkIdInfo... networkIds) {
	// TODO Auto-generated method stub
	return null;
    }

    /* (non-Javadoc)
     * @see org.bitmouth.rest.api.BitMouthClient#conference(org.bitmouth.rest.api.plumbing.ConferenceInfo)
     */
    public ConferenceTemplate conference(ConferenceInfo conference) {
	// TODO Auto-generated method stub
	return null;
    }
        
}
