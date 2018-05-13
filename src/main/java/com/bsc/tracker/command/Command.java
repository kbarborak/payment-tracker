package com.bsc.tracker.command;

import java.io.PrintWriter;

public interface Command {

    /**
     * Flag which describes if command requires application exit.
     *
     * @return
     */
    Boolean isRequiredExit();

    /**
     * Executes command logic
     *
     * @param input  Input argument.
     * @param output Console output.
     */
    void execute(String input, PrintWriter output);
}
