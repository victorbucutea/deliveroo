package com.deliveroo.takehome;

import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * Calculates exploded times fiven a cron text of 3 types :
 * 1. Range: 1-3 exploded to 1,2,3
 * 2. Fixed Values: 1,2,3 exploded to ... 1,2,3
 * 3. Intervals: *\/10  exploded to 0,10,20,30,...
 */
public class CronField {

    private final String incomingText;
    private CronFieldType type;
    private Set<Integer> values = new TreeSet<>();

    public CronField(String incomingText, CronFieldType type) throws InvalidCronFieldException {
        this.type = type;
        this.incomingText = incomingText;

        parseFixedValues();

        parseRangeOfValues();

        parseIntervals();

        if (values.isEmpty()) {
            values.add(parseNumber(incomingText));
        }
    }

    public Set<Integer> getValues() {
        return values;
    }

    public String toString() {
        return values.stream().map(Object::toString).collect(Collectors.joining(" "));
    }

    private void parseIntervals() throws InvalidCronFieldException {
        if (incomingText.startsWith("*")) {
            int interval = 1;
            String[] intervals = incomingText.split("/");
            if (intervals.length > 2) {
                throw new InvalidCronFieldException("Number " + incomingText + " for " + type + "has too many intervals");
            }
            if (intervals.length == 2) {
                interval = parseNumber(intervals[1]);
            }
            populateValues(type.min, type.max, interval);
        }
    }

    private void parseRangeOfValues() throws InvalidCronFieldException {
        String[] range = incomingText.split("-");
        if (range.length == 2) {
            int start = parseNumber(range[0]);
            int end = parseNumber(range[1]);
            populateValues(start, end, 1);
        }
    }

    private void parseFixedValues() throws InvalidCronFieldException {
        String[] fixedDates = incomingText.split(",");
        if (fixedDates.length > 1) {
            //fixed values
            for (String date : fixedDates) {
                int e = parseNumber(date);
                // pass value to populate() to validate min max ranges
                populateValues(e, e, 1);
            }
        }
    }

    private void populateValues(int start, int end, int increment) throws InvalidCronFieldException {
        if (increment == 0) {
            throw new InvalidCronFieldException("Number " + incomingText + " for " + type + " interval is 0");
        }
        if (end < start) {
            throw new InvalidCronFieldException("Number " + incomingText + " for " + type + " ends before it starts");
        }
        if (start < type.min || end > type.max) {
            throw new InvalidCronFieldException("Number " + incomingText + " for " + type + " is outside valid range (" + type.min + "-" + type.max + ")");
        }
        for (int i = start; i <= end; i += increment) {
            values.add(i);
        }
    }

    private Integer parseNumber(String no) throws InvalidCronFieldException {
        try {
            return Integer.parseInt(no);
        } catch (NumberFormatException nfe) {
            throw new InvalidCronFieldException("Invalid number '" + no + "' in field " + type + ": " + nfe.getMessage());
        }
    }
}
