package com.trustingsocial.assignment.task1.comparator;

import com.trustingsocial.assignment.task1.model.PhoneNumber;

import java.util.Comparator;

public class SortingPhoneNumber implements Comparator<PhoneNumber> {

    @Override
    public int compare(PhoneNumber o1, PhoneNumber o2) {
        return o1.getPhoneNumber().compareTo(o2.getPhoneNumber());
    }

}
