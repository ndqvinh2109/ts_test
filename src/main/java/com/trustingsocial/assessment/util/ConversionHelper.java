package com.trustingsocial.assessment.util;

import com.trustingsocial.assessment.model.PhoneNumber;

public class ConversionHelper {

    public static StringBuilder from(PhoneNumber p) {
        StringBuilder stringBuilder = buildActivationPhone(p);
        stringBuilder.append(",");
        if (p.getEndDate() != null && !p.getEndDate().isEmpty()) {
            stringBuilder.append(p.getEndDate());
        }
        return stringBuilder;
    }

    public static PhoneNumber to(String[] array) {
        PhoneNumber phoneNumber = new PhoneNumber();
        phoneNumber.setPhoneNumber(array[0]);
        phoneNumber.setStartDate(array[1]);
        if (array.length == 3) {
            phoneNumber.setEndDate(array[2]);
        }
        return phoneNumber;
    }

    public static StringBuilder buildActivationPhone(PhoneNumber foundPhoneNumber) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(foundPhoneNumber.getPhoneNumber());
        stringBuilder.append(",");
        stringBuilder.append(foundPhoneNumber.getStartDate());
        return stringBuilder;
    }

}
