/**
 * 
 */
package com.test.thrift;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

/**
 * @author wq
 *
 */
public class WqClient {
	
	public void startClient() {
        TTransport transport;
        try {
            transport = new TSocket("10.12.1.42", 50088);
            TProtocol protocol = new TBinaryProtocol(transport);
            Wq.Client client = new Wq.Client(protocol);
            transport.open();
            client.ping(20130705);
            client.hello("hello everybody hello you hello me hello hello ha ha lou");
            transport.close();
        } catch (TTransportException e) {
            e.printStackTrace();
        } catch (TException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        WqClient client = new WqClient();
        client.startClient();
    }

}
