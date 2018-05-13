package com.bsc.tracker.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class QuitCommandTest {

    @Test
    void quitTest() {
        assertTrue(new QuitCommand().isRequiredExit());
    }
}
