package com.test.thrift.test;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

public class Client {
	private static final String HOST = "10.0.7.216";
	private static final int PORT = 55501;
	
	public static void main(String[] args) {
		Client client = new Client();
		client.startClient();
	}

	public void startClient() {
		TTransport transport;
		try {
			transport = new TSocket(HOST, PORT);
			TProtocol protocol = new TBinaryProtocol(transport);
			ThriftTest.Client client = new ThriftTest.Client(protocol);
			transport.open();
		//	client.echo(PORT);
			client.sayHello("impala-shell -f /home/boco/bao/impala/w2");
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
	}
}
