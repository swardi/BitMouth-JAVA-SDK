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
import org.bitmouth.rest.api.resources.NetworkIdInfo;

/**
 * @author Shamaila Tahir
 *
 */
public interface NetworkIdsTemplate {
     
    /**
     * This method can be called to determine if a network ID has been registered with the Bitmouth system
     * @param networkId
     * @return boolean true if this network id is registered
     * @throws AuthorizationException the network ID exists but it not associated with the specified application 
     */
    public boolean isRegistered(String networkId) throws AuthorizationException;
    
    /**
     * This method can be called to determine if a network ID has been registered and is associated 
     * with a particular application in the Bitmouth system.
     * @param networkId
     * @return RegistrantInfo
     * @throws AuthorizationException the network ID exists but it not associated with the specified application
     * @throws ResourceNotFoundException the networkId is not registered. 
     */
    public boolean getNetworkIdAssociatedWithApplication(String networkId) throws AuthorizationException;

    /**
     * This method registers a network ID and phone number pair and relates them to the current application. 
     * @param networkId
     * @param phone
     * @return RegistrantInfo of new registration
     * @throws BadRequestException One or more parameters were invalid or this (phone, networkid) pair is already registered.
     * @throws BadGatewayException Unable to start the phone authorization process. 
     */
    public NetworkIdInfo register(String networkId, String phone) throws BadRequestException,BadGatewayException;
}
