package com.test.thrift.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.thrift.TException;

public class Impl implements ThriftTest.Iface{

	@Override
	public void echo(int length) throws TException {
		System.out.printf("echo %s\n", length);
	}

	@Override
	public void sayHello(String str) throws TException {
		String s = null;
		Runtime run = Runtime.getRuntime();

		InputStream in = null;
		BufferedReader br = null;
		
		String[] cmd = {"/bin/sh", "-c", str};
		try {
			// run.exec("cmd /k shutdown -s -t 3600");
			Process process = run.exec(cmd);
			in = process.getInputStream();
			br = new BufferedReader(new InputStreamReader(in));
			while ((s = br.readLine()) != null) {
				System.out.println(s);
				System.out.println();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					System.out.println("BufferedReader closed error");
					e.printStackTrace();
				}
			}
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					System.out.println("InputStream closed error");
					e.printStackTrace();
				}
			}
		}
	}

	
}
