package org.mpiztools.clinter.thread;

import org.mpiztools.clinter.cmd.Command;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.*;

public class CommandTask implements Callable<String> {

    private final Command cmd;
    private volatile Process proc;

    private boolean isOk;

    public CommandTask(Command cmd) throws IOException {
        this.cmd = cmd;
        proc = Runtime.getRuntime().exec(cmd.toString());
    }

    public CommandTask(String cmd) throws IOException {
        this(new Command(cmd));
    }

    String getCommandOutput() throws IOException {
        StringBuilder sbOutput = new StringBuilder();
        InputStream inputStream = proc.getInputStream();
        isOk = inputStream.available() > 0;

        InputStream scanStream = isOk ?
                inputStream : proc.getErrorStream();

        int read;
        while ((read = scanStream.read()) != -1) {
            sbOutput.append((char)read);
        }
        System.out.println("ExitValue: "+proc.exitValue());
        return sbOutput.toString();
    }

    public boolean isOK() {
        return isOk;
    }

    @Override
    public String call() throws Exception {
        proc.waitFor();
        return getCommandOutput();
    }
}
