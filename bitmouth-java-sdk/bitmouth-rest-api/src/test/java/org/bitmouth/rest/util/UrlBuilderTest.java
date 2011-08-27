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
package org.bitmouth.rest.util;

import junit.framework.TestCase;

/**
 * @author Shamaila Tahir
 *
 */
public class UrlBuilderTest extends TestCase{
    String baseAPIUrl="https://api4.bitmouth.com/api/rest/";
    String appKey="f180804f-5eda-4e6b-8f4e-ecea52362396";
    
    public void testUrlWithoutParamsWithoutSegments(){
	
	UrlBuilder urlBuilder = new UrlBuilder(baseAPIUrl);
	String url = urlBuilder.buildUrl();
	System.out.println(url);
	assertNotNull(url);
	assertEquals("https://api4.bitmouth.com/api/rest", url);
    }
       
    public void testUrlWithOneParamWithoutSegments(){

	UrlBuilder urlBuilder = new UrlBuilder(baseAPIUrl);
	urlBuilder.addGetParameter("appKey", appKey);
	String url = urlBuilder.buildUrl();
	System.out.println(url);
	assertNotNull(url);
	assertEquals("https://api4.bitmouth.com/api/rest?appKey=f180804f-5eda-4e6b-8f4e-ecea52362396", url);
    }
    
    public void testUrlWithThreeParamsWithoutSegments(){

	UrlBuilder urlBuilder = new UrlBuilder(baseAPIUrl);
	urlBuilder.addGetParameter("appKey", appKey);
	urlBuilder.addGetParameter("action", "create");
	urlBuilder.addGetParameter("networkId", "12325235");
	String url = urlBuilder.buildUrl();
	System.out.println(url);
	assertNotNull(url);
	assertEquals("https://api4.bitmouth.com/api/rest?networkId=12325235&action=create&appKey=f180804f-5eda-4e6b-8f4e-ecea52362396", url);
    }
    
    public void testUrlWithoutParamsWithOneSegment(){
	
	UrlBuilder urlBuilder = new UrlBuilder(baseAPIUrl);
	urlBuilder.addPathSegment("media");
	String url = urlBuilder.buildUrl();
	System.out.println(url);
	assertNotNull(url);
	assertEquals("https://api4.bitmouth.com/api/rest/media", url);
    }
    
  public void testUrlWithoutParamsWithThreeSegments(){
	
	UrlBuilder urlBuilder = new UrlBuilder(baseAPIUrl);
	urlBuilder.addPathSegment("media");
	urlBuilder.addPathSegment("start");
	urlBuilder.addPathSegment("recording");
	String url = urlBuilder.buildUrl();
	System.out.println(url);
	assertNotNull(url);
	assertEquals("https://api4.bitmouth.com/api/rest/media/start/recording", url);
    }
  
  public void testUrlWithParamsWithSegments(){
	
	UrlBuilder urlBuilder = new UrlBuilder(baseAPIUrl);
	urlBuilder.addPathSegment("media");
	urlBuilder.addGetParameter("action", "create");
	urlBuilder.addGetParameter("networkId", "12325235");
	String url = urlBuilder.buildUrl();
	System.out.println(url);
	assertNotNull(url);
	assertEquals("https://api4.bitmouth.com/api/rest/media?networkId=12325235&action=create", url);
  }

  public void testUrlWithMultiParamsWithoutSegments(){
      	UrlBuilder urlBuilder = new UrlBuilder(baseAPIUrl);
      	urlBuilder.addGetParameter("networkId", "111111");
      	urlBuilder.addGetParameter("networkId", "22222");
      	urlBuilder.addGetParameter("networkId", "33333");
      	urlBuilder.addGetParameter("networkId", "444444");
      	urlBuilder.addGetParameter("action", "testMultiParams");
	String url = urlBuilder.buildUrl();
	System.out.println(url);
	assertNotNull(url);
	assertEquals("https://api4.bitmouth.com/api/rest?networkId=111111&networkId=22222&networkId=33333&networkId=444444&action=testMultiParams", url);
      
  }
  
  public void testUrlWithManyMultiParamsWithoutSegments(){
    	UrlBuilder urlBuilder = new UrlBuilder(baseAPIUrl);
    	urlBuilder.addGetParameter("action", "testMultiParams")
        	  .addGetParameter("networkId", "111111")
        	  .addGetParameter("networkId", "22222")
        	  .addGetParameter("networkId", "33333")
        	  .addGetParameter("networkId", "444444");
    	
    	urlBuilder.addGetParameter("param2", "111111")
    		  .addGetParameter("param2", "22222")
    		  .addGetParameter("param2", "33333")
    		  .addGetParameter("param2", "444444");
    	
	String url = urlBuilder.buildUrl();
	System.out.println(url);
	assertNotNull(url);
	assertEquals("https://api4.bitmouth.com/api/rest?param2=111111&param2=22222&param2=33333&param2=444444&networkId=111111&networkId=22222&networkId=33333&networkId=444444&action=testMultiParams", url);
    
}
  
  public void testUrlWithMultiParamsWithSegments(){
    	UrlBuilder urlBuilder = new UrlBuilder(baseAPIUrl);
    	urlBuilder.addGetParameter("networkId", "111111")
        	  .addGetParameter("networkId", "22222")
        	  .addGetParameter("networkId", "33333")
        	  .addGetParameter("networkId", "444444")
        	  .addPathSegment("media")
        	  .addGetParameter("action", "testMultiParams");
	String url = urlBuilder.buildUrl();
	System.out.println(url);
	assertNotNull(url);
	assertEquals("https://api4.bitmouth.com/api/rest/media?networkId=111111&networkId=22222&networkId=33333&networkId=444444&action=testMultiParams", url);
    
}
  

}
