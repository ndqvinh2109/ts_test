package com.trustingsocial.assignment.task1.comparator;

import com.trustingsocial.assignment.task1.model.PhoneNumber;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;

public class SortingStartDate implements Comparator<PhoneNumber> {

    @Override
    public int compare(PhoneNumber o1, PhoneNumber o2) {
        DateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return f.parse(o1.getStartDate()).compareTo(f.parse(o2.getStartDate()));
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }

}
