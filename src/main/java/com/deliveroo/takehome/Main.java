package com.deliveroo.takehome;

import java.util.Arrays;

public class Main {


    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Expected [minute] [hour] [day of month] [day of week] [command] but got :" + Arrays.toString(args));
            return;
        }

        try {

            CronExpression expr = new CronExpression(args[0]);
            System.out.println(expr);

        } catch (InvalidCronFieldException invalidCronExpression) {
            System.err.println(invalidCronExpression.getMessage());
        }
    }
}
