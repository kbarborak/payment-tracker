package com.bsc.tracker.command;

import com.bsc.tracker.Payment;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Loader that is capable of loading payments from file instead user input.
 */
public class FilePaymentLoader {

    /**
     * @param path Path to file.
     * @return
     * @throws IOException
     */
    public List<Payment> load(String path) throws IOException {
        return load(new FileReader(path));
    }

    /**
     * @param stream Input stream.
     * @return
     * @throws IOException
     */
    public List<Payment> load(InputStream stream) throws IOException {
        return load(new InputStreamReader(stream));
    }

    private List<Payment> load(Reader reader) throws IOException {
        List<Payment> payments = new LinkedList<>();

        try (BufferedReader breader = new BufferedReader(reader)) {
            String line = breader.readLine();

            while (line != null) {
                payments.add(new Payment(line.trim()));
                line = breader.readLine();
            }
        }

        return payments;
    }
}
