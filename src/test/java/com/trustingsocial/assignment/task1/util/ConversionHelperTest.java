package com.trustingsocial.assignment.task1.util;

import com.trustingsocial.assignment.task1.model.PhoneNumber;
import static org.hamcrest.MatcherAssert.*;

import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;

public class ConversionHelperTest {

    @Test
    public void mapPhoneToStringBuilder() {
        PhoneNumber phoneNumber = new PhoneNumber();
        phoneNumber.setPhoneNumber("0987000011");
        phoneNumber.setStartDate("2016-03-01");
        phoneNumber.setEndDate("2016-05-01");

        String actual = ConversionHelper.from(phoneNumber).toString();

        assertThat(actual, Is.is("0987000011,2016-03-01,2016-05-01"));
    }

    @Test
    public void mapArraytoPhone() {
        PhoneNumber phoneNumber = new PhoneNumber();
        phoneNumber.setPhoneNumber("0987000011");
        phoneNumber.setStartDate("2016-03-01");
        phoneNumber.setEndDate("2016-05-01");

        String str = "0987000011,2016-03-01,2016-05-01";

        PhoneNumber actual = ConversionHelper.to(str.split(","));
        assertThat(actual.getPhoneNumber(), Is.is("0987000011"));
    }

}
