package org.mpiztools.clinter;

import org.mpiztools.clinter.cmd.Command;
import org.mpiztools.clinter.thread.CommandTask;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Clinter {

    private ExecutorService executor;

    public Clinter() {
        executor = Executors.newCachedThreadPool();
    }

    public Future<String> execute(Command cmd) throws IOException {
        return executor.submit(new CommandTask(cmd));
    }

    public Future<String> execute(String cmd) throws IOException {
        return execute(new Command(cmd));
    }

    public Future<String> execute(CommandTask task) throws IOException {
        return executor.submit(task);
    }

    public void destroyAll() {
        executor.shutdownNow();
    }

}
