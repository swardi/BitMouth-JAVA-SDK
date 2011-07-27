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
import org.bitmouth.rest.api.exceptions.NoContentException;
import org.bitmouth.rest.api.exceptions.ResourceNotFoundException;
import org.bitmouth.rest.api.resources.MediaInfo;
import org.bitmouth.rest.api.resources.NetworkIdInfo;
import org.bitmouth.rest.api.resources.UploadInfo;

/**
 * This class encapsulates a Media identifier and allows several operations over 
 * the the media object identified by that identifier
 * 
 * @see BitMouthClient#media(String)
 * @see BitMouthClient#getMediafromMediaId(String) 
 * @author Shamaila Tahir
 */
public interface MediaTemplate {
        
    /** 
     * This method causes the specified networkid to be called and played a "record greeting" prompt. 
     * The status of the recording can subsequently be determined by calling the "media status" method 
     * and supplying the appropriate Media ID
     * 
     * @param networkid The network id to be called 
     * @return MediaInfo when record request had been accepted
     * @throws BadRequestException Missing parameter(s)
     * @throws ResourceNotFoundException The application key is invalid or the application is not associated with the networkid.
     * @throws BadGatewayException Unable to initiate phone call at this time. 
     */
    public MediaInfo record(NetworkIdInfo networkid) throws BadRequestException, ResourceNotFoundException, BadGatewayException;
    
    /**
     * This method causes the specified networkid to be called and played a previously-recorded greeting. 
     * 
     * @param networkid The network id to be called
     * @return MediaInfo when the "blast" request has been accepted.
     * @throws BadRequestException Missing parameter(s).
     * @throws ResourceNotFoundException The application key is invalid or the application is not associated with the networkid.
     * @throws BadGatewayException Unable to initiate phone call at this time.
     */
    public MediaInfo blast(NetworkIdInfo networkid) throws BadRequestException, ResourceNotFoundException, BadGatewayException;
    
    /**
     * This method is used to request permission to upload a file. To use this method, the application must first 
     * obtain a media ID. When successful, this method returns a grant code that may be used to perform a single 
     * file upload. The grant code is valid for twenty-four hours after issuance.
     * 
     * @return UploadInfo encapsulating the information about Upload Grant
     * @throws BadRequestException Missing parameter(s)
     * @throws ResourceNotFoundException The application key is invalid or the application is not associated with the networkid.
     * @throws BadGatewayException Unable to initiate phone call at this time. 
     */
    public UploadInfo grantUpload() throws BadRequestException, ResourceNotFoundException, BadGatewayException;
    
    /**  
     * This method is used to request remove a media instance. 
     * 
     * @param mediaId Media id to be removed.
     * @return MediaInfo when the "remove" request has been accepted.
     * @throws BadRequestException Missing parameter(s).
     * @throws ResourceNotFoundException The application key is invalid or the application is not associated with the networkid.
     * @throws BadGatewayException Unable to initiate phone call at this time.
     */
    public MediaInfo removeMedia() throws BadRequestException, ResourceNotFoundException, BadGatewayException;
    
    /**
     * This method retrieves status information about the Media ID and the operation in progress, if any. 
     * @return MediaInfo if the media id is valid and content is found against that media id
     * @throws ResourceNotFoundException  The application key or networkid are invalid. 
     * @throws NoContentException No media content exists for this Media ID.
     */
    public MediaInfo status();
}
