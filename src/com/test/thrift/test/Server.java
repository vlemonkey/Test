package com.test.thrift.test;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TBinaryProtocol.Factory;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.server.TThreadPoolServer.Args;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;

import com.test.thrift.test.ThriftTest.Iface;
import com.test.thrift.test.ThriftTest.Processor;

public class Server {
	
	public static void main(String[] args) {
		Server server = new Server();
		server.startServer();
		System.out.println("server start...");
	}

	public void startServer(){
		TServerSocket tsSocket;
		try {
			tsSocket = new TServerSocket(55501);
		} catch (TTransportException e) {
			e.printStackTrace();
			return;
		}
		ThriftTest.Processor<Iface> prcess = new Processor<Iface>(new Impl());
		Factory portFactory = new TBinaryProtocol.Factory(true, true);
		
		Args args = new Args(tsSocket);
		args.processor(prcess);
		args.protocolFactory(portFactory);
		
		TServer server = new TThreadPoolServer(args);
		server.serve();
	}
}
