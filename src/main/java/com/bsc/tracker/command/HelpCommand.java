package com.bsc.tracker.command;

import java.io.PrintWriter;

public class HelpCommand implements Command {

    @Override
    public Boolean isRequiredExit() {
        return true;
    }

    /**
     * @param input  Input argument.
     * @param output Console output.
     * @throws RuntimeException
     */
    @Override
    public void execute(String input, PrintWriter output) {
        throw new RuntimeException("Too many arguments. Use payment-tracker.jar [\"filename\"].");
    }
}
