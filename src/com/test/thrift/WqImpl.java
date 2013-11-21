/**
 * 
 */
package com.test.thrift;

import org.apache.thrift.TException;

/**
 * @author lenovo
 *
 */
public class WqImpl implements Wq.Iface{
	
	public void ping(int length) throws TException {
		System.out.println("calling ping ,length=" + length);
	}

	
	public void hello(String str) throws TException {
		System.out.println("str:"+str);
	}

}
