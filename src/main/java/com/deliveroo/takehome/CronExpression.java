package com.deliveroo.takehome;

import static java.lang.String.format;


/**
 * A CronExpression is an representation of a cron string with all members:
 * [minute] [hour] [day of month] [day of week] [command]
 *
 * It will validate basic syntax and delegate calculation of each member of the cron string (month, year ) to CronField,
 * which in turn will calculate the relevant exploded times (e.g. 1-3 will be 1,2,3 ) .
 *
 */
public class CronExpression {

    private CronField minutes;
    private CronField hours;
    private CronField dayOfMonth;
    private CronField month;
    private CronField dayOfWeek;
    /**
     * not used for current assignment
     */
    private CronField year;

    private String command;

    public CronExpression(String arg) throws InvalidCronFieldException {
        String[] cronMembers = arg.split("\\s+");
        if (cronMembers.length != 6) {
            throw new InvalidCronFieldException("Expected [minute] [hour] [day of month] [day of week] [command] but got :" + arg);
        }

        minutes = new CronField(cronMembers[0], CronFieldType.MINUTES);
        hours = new CronField(cronMembers[1], CronFieldType.HOURS);
        dayOfMonth = new CronField(cronMembers[2], CronFieldType.DAY_OF_MONTH);
        month = new CronField(cronMembers[3], CronFieldType.MONTH);
        dayOfWeek = new CronField(cronMembers[4], CronFieldType.DAY_OF_WEEK);
        command = cronMembers[5];

    }

    public String toString() {
        StringBuffer b = new StringBuffer();
        b.append(format("%-14s%s\n", "minute", minutes.toString()));
        b.append(format("%-14s%s\n", "hour", hours.toString()));
        b.append(format("%-14s%s\n", "day of month", dayOfMonth.toString()));
        b.append(format("%-14s%s\n", "month", month.toString()));
        b.append(format("%-14s%s\n", "day of week", dayOfWeek.toString()));
        b.append(format("%-14s%s\n", "command", command));
        return b.toString();
    }

}
