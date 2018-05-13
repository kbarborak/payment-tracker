package com.bsc.tracker.command;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HelpCommandTest {

    @Test
    void helpTest() {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        PrintWriter writer = new PrintWriter(stream);
        HelpCommand command = new HelpCommand();

        assertThrows(RuntimeException.class, () -> command.execute(null, writer));
        assertTrue(command.isRequiredExit());
    }
}
