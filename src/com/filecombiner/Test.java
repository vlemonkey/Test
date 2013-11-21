package com.filecombiner;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.SequenceInputStream;
import java.util.Enumeration;
import java.util.Vector;

public class Test {

	public static void main(String[] args) throws Exception{
		FileInputStream fis1 = new FileInputStream("D:/1.txt");
		FileInputStream fis2 = new FileInputStream("D:/2.txt");
		FileInputStream fis3 = new FileInputStream("D:/3.txt");

		Vector<InputStream> inputStreams = new Vector<InputStream>();
		inputStreams.add(fis1);
		inputStreams.add(fis2);
		inputStreams.add(fis3);

		Enumeration<InputStream> enu = inputStreams.elements();
		SequenceInputStream sis = new SequenceInputStream(enu);

		int count = 0;
		final int BUFFER_SIZE = 64 * 1024 - 2;
		byte[] data = new byte[BUFFER_SIZE];
		ByteArrayOutputStream bStream = new ByteArrayOutputStream();
		try {
			while ((count = sis.read(data, 0, BUFFER_SIZE)) != -1) {
					bStream.write(data);
			}
			
			System.out.println("final combine length:" + bStream.toByteArray().length);
			bStream.flush();
			bStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		FileWriter fw = new FileWriter(new File("D:/4.txt"));
		int oneByte;
		while ((oneByte = sis.read()) != -1) {
			System.out.write(oneByte);
			fw.write(oneByte);
		}
		fw.flush();
		fw.close();
		System.out.flush();
	}
}
