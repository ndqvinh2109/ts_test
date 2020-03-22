package com.trustingsocial.assignment.comparator;

import com.trustingsocial.assignment.model.PhoneNumber;

import java.util.Comparator;

public class SortingPhoneNumber implements Comparator<PhoneNumber> {

    @Override
    public int compare(PhoneNumber o1, PhoneNumber o2) {
        return o1.getPhoneNumber().compareTo(o2.getPhoneNumber());
    }

}
