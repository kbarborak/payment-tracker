package com.bsc.tracker.command;

import java.io.PrintWriter;

public class QuitCommand implements Command {

    @Override
    public Boolean isRequiredExit() {
        return true;
    }

    @Override
    public void execute(String input, PrintWriter output) {
        output.append("Bye \\o_");
    }
}
