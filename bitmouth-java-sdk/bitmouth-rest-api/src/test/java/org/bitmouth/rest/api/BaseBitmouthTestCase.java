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

import org.bitmouth.rest.util.UrlBuilder;
import org.bitmouth.rest.util.UrlBuilder.URLBuilderFactory;

import junit.framework.TestCase;

/**
 * @author Shamaila Tahir
 *
 */
public abstract class BaseBitmouthTestCase extends TestCase {
    BitMouthClient client;
    URLBuilderFactory urlBuilder=new UrlBuilder.URLBuilderFactory("https://api4.bitmouth.com/api/rest","f180804f-5eda-4e6b-8f4e-ecea52362396");
    
    /* (non-Javadoc)
        * @see junit.framework.TestCase#setUp()
        */
       @Override
       protected void setUp() throws Exception {
   	super.setUp();
   	//The value of App key does not matter in this case
   	client = BitMouth.createClient("f180804f-5eda-4e6b-8f4e-ecea52362396", "https://api4.bitmouth.com/api/rest");
       }
}
