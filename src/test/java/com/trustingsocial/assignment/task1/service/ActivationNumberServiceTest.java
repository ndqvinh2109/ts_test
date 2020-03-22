package com.trustingsocial.assignment.task1.service;

import com.trustingsocial.assignment.task1.TestCaseHelper;
import com.trustingsocial.assignment.task1.comparator.SortingPhoneNumber;
import com.trustingsocial.assignment.task1.comparator.SortingStartDate;
import com.trustingsocial.assignment.task1.model.PhoneNumber;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

public class ActivationNumberServiceTest {

    PhoneNumberService phoneNumberService;

    @BeforeEach
    public void init() {
        phoneNumberService = new PhoneNumberService();
    }

    @Test
    public void shouldReturnActivationDateGap_When_UserTerminatedAndReusedPhoneNumber() {
        List<PhoneNumber> phoneNumbers = TestCaseHelper.buildTestCaseA();

        Collections.sort(phoneNumbers, new SortingStartDate());


        StringBuilder builder = phoneNumberService.findActivatePhoneNumber(phoneNumbers);
        String str = builder.toString();

        Assertions.assertEquals(str, "0987000001,2016-06-01");
    }

    @Test
    public void shouldReturnFirstActivationDate_When_ThereIsNoTerminatedNumber() {
        List<PhoneNumber> phoneNumbers = TestCaseHelper.buildTestCaseB();

        Collections.sort(phoneNumbers, new SortingPhoneNumber());

        StringBuilder builder = phoneNumberService.findActivatePhoneNumber(phoneNumbers);
        String str = builder.toString();

        Assertions.assertEquals(str, "0987000002,2016-02-01");
    }

    @Test
    public void shouldReturnEmptyString_WhenPhoneNumberNotUsing() {
        List<PhoneNumber> phoneNumbers = TestCaseHelper.buildTestCaseC();

        Collections.sort(phoneNumbers, new SortingPhoneNumber());

        StringBuilder builder = phoneNumberService.findActivatePhoneNumber(phoneNumbers);
        String str = builder.toString();

        Assertions.assertEquals(str, "");

        phoneNumbers = TestCaseHelper.buildTestCaseD();

        Collections.sort(phoneNumbers, new SortingPhoneNumber());

        builder = phoneNumberService.findActivatePhoneNumber(phoneNumbers);
        str = builder.toString();

        Assertions.assertEquals(str, "");
    }


}
