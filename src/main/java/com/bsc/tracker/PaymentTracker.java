package com.bsc.tracker;

import com.bsc.tracker.command.*;

import java.io.PrintWriter;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Payment tracker application.
 */
public class PaymentTracker {

    public static void main(String[] args) {
        PrintWriter output = System.console().writer();
        PaymentStore store = new PaymentStore();
        CommandExecutor executor = new CommandExecutor(output);

        showHelpIfNeeded(args, executor);
        loadPaymentsFromFileIfRequired(args, executor, store);
        setUpBackgroundTasks(store);
        run(executor, store);
    }

    private static void showHelpIfNeeded(String[] args, CommandExecutor executor) {
        if (args.length > 1) {
            executor.execute(new HelpCommand(), null);
        }
    }

    private static void loadPaymentsFromFileIfRequired(String[] args, CommandExecutor executor, PaymentStore store) {
        if (args.length == 1) {
            executor.execute(new LoadPaymentsFromFileCommand(store, new FilePaymentLoader()), args[0]);
        }
    }

    private static void setUpBackgroundTasks(PaymentStore store) {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(new PrintPaymentsWithUsdCommand(store, new ExchangeConverter()), 1L, 1L, TimeUnit.MINUTES);
    }

    private static void run(CommandExecutor executor, PaymentStore store) {
        InteractiveCommandFactory commandFactory = new InteractiveCommandFactory(store);
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (true) {
            input = input.trim();
            executor.execute(commandFactory.create(input), input);
            input = sc.nextLine();
        }
    }
}
