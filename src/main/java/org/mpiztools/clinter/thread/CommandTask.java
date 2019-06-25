package org.mpiztools.clinter.thread;

import org.mpiztools.clinter.cmd.Command;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.*;

public class CommandTask implements Callable<String> {

    private final Command cmd;
    private Process proc;

    private boolean cancelled;

    public CommandTask(Command cmd) throws IOException {
        this.cmd = cmd;
        proc = Runtime.getRuntime().exec(cmd.toString());
        cancelled = false;
    }

    String getCommandOutput() throws IOException {
        StringBuilder sbOutput = new StringBuilder();

        InputStream inputStream = proc.getInputStream();
        InputStream scanStream = inputStream.available() > 0 ?
                inputStream : proc.getErrorStream();

        int read;
        while ((read = scanStream.read()) != -1) {
            sbOutput.append((char)read);
        }
        return sbOutput.toString();
    }

    public Process getProc() {
        return proc;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    @Override
    public String call() throws Exception {
        proc.waitFor();
        return getCommandOutput();
    }
}
