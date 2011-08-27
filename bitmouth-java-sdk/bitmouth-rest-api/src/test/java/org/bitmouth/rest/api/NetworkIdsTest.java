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

import org.bitmouth.rest.api.resources.NetworkIdInfo;


/**
 * @author Shamaila Tahir
 *
 */
public class NetworkIdsTest extends BaseBitmouthTestCase{
    
	public void testRegisterNetworkId(){
	     NetworkIdInfo newNetworkInfo = client.networkIds().register("123", "349582");
	     assertNotNull(newNetworkInfo);          
	 }
	
public void testRegisteredNetworkId(){
     boolean isRegistered = client.networkIds().isRegistered("123");
     System.out.println("isRegistered 123 " + isRegistered);
     assertTrue( isRegistered );
 }
 
 public void testUnregisteredNetworkId(){
     boolean isRegistered = client.networkIds().isRegistered("124");
     System.out.println("isRegistered 124 " + isRegistered);
     assertFalse( isRegistered );          
 }
 
 public void testNetworkIdRelated(){
     assertTrue(client.networkIds().getNetworkIdAssociatedWithApplication("123"));          
 }
 
 
 
}
