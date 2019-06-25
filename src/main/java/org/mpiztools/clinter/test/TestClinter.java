package org.mpiztools.clinter.test;

import org.mpiztools.clinter.Clinter;
import org.mpiztools.clinter.cmd.Command;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class TestClinter {
    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        long ti, tf;

        Clinter clinter = new Clinter();
        Future<String> ifconfig = clinter.execute(new Command("nmcli"));
        ti = System.currentTimeMillis();
        while (!ifconfig.isDone()) {
        }
        tf = System.currentTimeMillis();
        System.out.println("-----------------------------");
        System.out.println("Command wait: "+(tf-ti));
        System.out.println(ifconfig.get());
    }
}
