package org.mpiztools.clinter.cmd;

import java.util.Arrays;

public class Command {
    private final String order;
    private final String[] options;

    public Command(String order, String... options) {
        this.order = order;
        this.options = options;
    }

    public Command(String strCmd) {
        if (strCmd.contains(" ")) {
            String[] split = strCmd.split(" ");
            order = split[0];
            options = new String[split.length-1];

            for (int i = 1; i < split.length; i++)
                options[i-1] = split[i];
        }
        else {
            order = strCmd;
            options = null;
        }
    }

    public String getOrder() {
        return order;
    }

    public String[] getOptions() {
        return options;
    }

    public String getOptionAt(int index) {
        return options[index];
    }

    @Override
    public String toString() {
        StringBuilder sbCmd = new StringBuilder();
        sbCmd.append(order);

        if (options != null) {
            for (int i = 0; i < options.length; i++)
                sbCmd.append(' ').append(options[i]);
        }
        return sbCmd.toString();
    }
}
