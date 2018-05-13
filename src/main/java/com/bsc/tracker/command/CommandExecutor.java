package com.bsc.tracker.command;

import java.io.PrintWriter;

/**
 * Wrapper for executing application commands.
 *
 * Main purpose for this wrapper is handling errors in commands, console output flushing and optionally terminating
 * JVM on behalf used command.
 */
public class CommandExecutor {

    private PrintWriter output;

    /**
     * @param output Output interface.
     */
    public CommandExecutor(PrintWriter output) {
        this.output = output;
    }

    /**
     * Execute command.
     *
     * @param command Command to execute.
     * @param input User input.
     */
    public void execute(Command command, String input) {
        Boolean isError = false;

        try {
            command.execute(input, output);
        } catch (RuntimeException e) {
            output.write(e.getMessage() + "\n");
            isError = true;
        }

        output.flush();

        if (command.isRequiredExit()) {
            System.exit(isError ? 1 : 0);
        }
    }
}
