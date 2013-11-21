/**
 * 
 */
package com.test.thrift;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TBinaryProtocol.Factory;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.server.TThreadPoolServer.Args;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;

import com.test.thrift.Wq.Processor;

/**
 * @author wq
 *
 */
public class WqServer {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void startServer() {
        try {

            TServerSocket serverTransport = new TServerSocket(50088);

            Wq.Processor process = new Processor(new WqImpl());

            Factory portFactory = new TBinaryProtocol.Factory(true, true);

            Args args = new Args(serverTransport);
            args.processor(process);
            args.protocolFactory(portFactory);

            TServer server = new TThreadPoolServer(args);
            server.serve();
        } catch (TTransportException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        WqServer server = new WqServer();
        server.startServer();
    }
}
