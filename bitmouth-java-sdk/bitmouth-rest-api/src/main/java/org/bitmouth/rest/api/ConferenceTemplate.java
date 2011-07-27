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

import org.bitmouth.rest.api.exceptions.AuthorizationException;
import org.bitmouth.rest.api.exceptions.BadGatewayException;
import org.bitmouth.rest.api.exceptions.BadRequestException;
import org.bitmouth.rest.api.exceptions.ResourceNotFoundException;
import org.bitmouth.rest.api.resources.ConferenceInfo;
import org.bitmouth.rest.api.resources.NetworkIdInfo;

/**
 * Allows several operations on existing conference calls. 
 * Conferences can be created with {@link BitMouthClient#createConference}. 
 * Instances of ConferenceTemplate can be created with {@link BitMouthClient#conference(ConferenceInfo)}
 * @see BitMouthClient#createConference(boolean, NetworkIdInfo...)
 * @see BitMouthClient#conference(ConferenceInfo)
 * @author Shamaila Tahir
 */
public interface ConferenceTemplate {

    
    /**
     * This method adds one or more additional network IDs (call legs) to the conference. 
     * @throws BadRequestException Missing or invalid parameter(s).
     * @throws BadGatewayException Unable to initiate phone(s) call at this time. 
     */
    public void addToConference(NetworkIdInfo... networkIdInfos) throws BadRequestException, BadGatewayException;
    
    /**
     * This method closes (removes) a conference and terminates any call legs in progress.  
     * @throws BadRequestException Missing or invalid parameter(s).
     * @throws ResourceNotFoundException The conference ID is invalid.
     * @throws BadGatewayException Unable to terminate conference calls. 
     */
    public void closeConference() throws BadRequestException, BadGatewayException,ResourceNotFoundException;
    
    /**
     * This method terminates the specified conference leg. 
     * 
     * @param networkId whose associated leg should be terminated 
     * @throws BadRequestException Missing or invalid parameter(s).
     * @throws AuthorizationException The specified network ID is not associated with the application identified by 
     * the application key.
     * @throws ResourceNotFoundException The conference ID is invalid.
     * @throws BadGatewayException Unable to terminate call at this time.
     */
    public void hangup(NetworkIdInfo networkId) throws BadRequestException, BadGatewayException,ResourceNotFoundException, AuthorizationException;
    
    
    /**
     * This method moves a call leg from this conference to another.
     * @throws BadRequestException Missing or invalid parameter(s).
     * @throws AuthorizationException The specified network ID is not associated with the application identified by the application key.
     * @throws BadGatewayException Unable to move call.      
     */ 
    public void move(ConferenceInfo toConference, NetworkIdInfo networkId);
    
    /**
     * This method mutes the specified call leg. 
     * @param networkId
     * @throws BadRequestException Missing or invalid parameter(s).
     * @throws AuthorizationException One or more of the specified network IDs is not associated with the application identified by the application key.
     * @throws BadGatewayException Unable to mute call. 
     */
    public void mute(NetworkIdInfo networkId);

    /**
     * This method un-mutes the specified call leg. 
     * @param networkId
     * @throws BadRequestException Missing or invalid parameter(s).
     * @throws AuthorizationException One or more of the specified network IDs is not associated with the application identified by the application key.
     * @throws BadGatewayException Unable to mute call. 
     */
    public void unmute(NetworkIdInfo networkId);

}
