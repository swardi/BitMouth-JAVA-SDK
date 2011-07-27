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

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.bitmouth.rest.impl.BitMouthClientImpl;

/**
 * @author Shamaila Tahir
 *
 */
public class BitMouth {

    /**
     * This method is responsible for resolving a BitMouthClient implementation class and instantiating it
     * @return
     */
    public static BitMouthClient createClient(String appKey){
	//Find the BitMouth Client factory class
	BitMouthClientFactory bitMouthClientFactory = lookupFactory();
	return bitMouthClientFactory.createClient(appKey);	
    }

    /**
     * @return
     */
    private static BitMouthClientFactory lookupFactory() {
	InputStream propertiesStream = BitMouth.class.getResourceAsStream("bitmouth.properties");
	Properties properties = new Properties();
	try {
	    properties.load(propertiesStream);
	} catch (IOException e) {
	    throw new RuntimeException(e);
	}
	String clientFactoryName = properties.getProperty("bitmouth.client.factory");
	try {
	    Class<?> clazz = Class.forName(clientFactoryName);
	    return (BitMouthClientFactory) clazz.newInstance();
	} catch (ClassNotFoundException e) {
	    throw new RuntimeException(e);
	} catch (InstantiationException e) {
	    throw new RuntimeException(e);	    
	} catch (IllegalAccessException e) {
	    throw new RuntimeException(e);
	}
    }
}
