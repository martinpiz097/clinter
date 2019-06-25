package org.mpiztools.clinter.test;

import org.mpiztools.clinter.Clinter;
import org.mpiztools.clinter.cmd.Command;
import org.mpiztools.clinter.thread.CommandTask;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

class TestClinter {
    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        long ti, tf;

        Clinter clinter = new Clinter();
        CommandTask task = new CommandTask("nmcli");
        System.out.println("Task: "+task.isOK());
        Future<String> ifconfig = clinter.execute(task);
        ti = System.currentTimeMillis();
        while (!ifconfig.isDone()) {
        }
        tf = System.currentTimeMillis();
        System.out.println("Task: "+task.isOK());


        System.out.println("-----------------------------");
        System.out.println("Command wait: "+(tf-ti));
        //System.out.println(ifconfig.get());

        clinter.destroyAll();
    }
}
