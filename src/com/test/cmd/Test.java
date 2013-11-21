package com.test.cmd;

import java.io.IOException;

public class Test {

	public static void main(String[] args) {
        try {
//            int timeout = Integer.parseInt(args[0]);
        	int timeout = 5000;
            CommandHelper.DEFAULT_TIMEOUT = timeout;
            CommandResult result = CommandHelper.exec("echo qwer");
            if (result != null) {
                System.out.println("Output:" + result.getOutput());
                System.out.println("Error:" + result.getError());
            }
        } catch (IOException ex) {
            System.out.println("IOException:" + ex.getLocalizedMessage());
        } catch (InterruptedException ex) {
            System.out.println("InterruptedException:" + ex.getLocalizedMessage());
        }
    }
}
