package com.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CmdTest {

	public static void main(String[] args) throws Exception {
		String s = null;
		String path = "ping -t www.baidu.com ";
		Runtime run = Runtime.getRuntime();

		try {
			// run.exec("cmd /k shutdown -s -t 3600");
			Process process = run.exec("cmd.exe /k  " + path);
			InputStream in = process.getInputStream();
			BufferedReader input = new BufferedReader(new InputStreamReader(in));
			while ((s = input.readLine()) != null) {
				System.out.println(s);
				System.out.println();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}