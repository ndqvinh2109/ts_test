package com.trustingsocial.assignment.task1;

import com.trustingsocial.assignment.task1.model.PhoneNumber;

import java.util.ArrayList;
import java.util.List;

public class TestCaseHelper {

    public static List<PhoneNumber> buildTestCaseB() {

        List<PhoneNumber> phoneNumbers = new ArrayList<>();
        PhoneNumber phoneNumber = new PhoneNumber();
        phoneNumber.setPhoneNumber("0987000002");
        phoneNumber.setStartDate("2016-02-01");
        phoneNumber.setEndDate("2016-03-01");
        phoneNumbers.add(phoneNumber);

        phoneNumber = new PhoneNumber();
        phoneNumber.setPhoneNumber("0987000002");
        phoneNumber.setStartDate("2016-03-01");
        phoneNumber.setEndDate("2016-05-01");
        phoneNumbers.add(phoneNumber);

        phoneNumber = new PhoneNumber();
        phoneNumber.setPhoneNumber("0987000002");
        phoneNumber.setStartDate("2016-05-01");
        phoneNumber.setEndDate(null);
        phoneNumbers.add(phoneNumber);

        return phoneNumbers;
    }

    public static List<PhoneNumber> buildTestCaseA() {

        List<PhoneNumber> phoneNumbers = new ArrayList<>();
        PhoneNumber phoneNumber = new PhoneNumber();
        phoneNumber.setPhoneNumber("0987000001");
        phoneNumber.setStartDate("2016-03-01");
        phoneNumber.setEndDate("2016-05-01");
        phoneNumbers.add(phoneNumber);

        phoneNumber = new PhoneNumber();
        phoneNumber.setPhoneNumber("0987000001");
        phoneNumber.setStartDate("2016-01-01");
        phoneNumber.setEndDate("2016-03-01");
        phoneNumbers.add(phoneNumber);

        phoneNumber = new PhoneNumber();
        phoneNumber.setPhoneNumber("0987000001");
        phoneNumber.setStartDate("2016-12-01");
        phoneNumber.setEndDate(null);
        phoneNumbers.add(phoneNumber);

        phoneNumber = new PhoneNumber();
        phoneNumber.setPhoneNumber("0987000001");
        phoneNumber.setStartDate("2016-09-01");
        phoneNumber.setEndDate("2016-12-01");
        phoneNumbers.add(phoneNumber);

        phoneNumber = new PhoneNumber();
        phoneNumber.setPhoneNumber("0987000001");
        phoneNumber.setStartDate("2016-06-01");
        phoneNumber.setEndDate("2016-09-01");
        phoneNumbers.add(phoneNumber);
        return phoneNumbers;
    }

    public static List<PhoneNumber> buildTestCaseC() {

        List<PhoneNumber> phoneNumbers = new ArrayList<>();
        PhoneNumber phoneNumber = new PhoneNumber();
        phoneNumber.setPhoneNumber("0987000001");
        phoneNumber.setStartDate("2016-03-01");
        phoneNumber.setEndDate("2016-05-01");
        phoneNumbers.add(phoneNumber);

        return phoneNumbers;
    }

    public static List<PhoneNumber> buildTestCaseD() {

        List<PhoneNumber> phoneNumbers = new ArrayList<>();
        PhoneNumber phoneNumber = new PhoneNumber();
        phoneNumber.setPhoneNumber("0987000001");
        phoneNumber.setStartDate("2016-03-01");
        phoneNumber.setEndDate("2016-05-01");
        phoneNumbers.add(phoneNumber);

        phoneNumber = new PhoneNumber();
        phoneNumber.setPhoneNumber("0987000001");
        phoneNumber.setStartDate("2016-01-01");
        phoneNumber.setEndDate("2016-03-01");
        phoneNumbers.add(phoneNumber);

        phoneNumber = new PhoneNumber();
        phoneNumber.setPhoneNumber("0987000001");
        phoneNumber.setStartDate("2016-09-01");
        phoneNumber.setEndDate("2016-12-01");
        phoneNumbers.add(phoneNumber);

        phoneNumber = new PhoneNumber();
        phoneNumber.setPhoneNumber("0987000001");
        phoneNumber.setStartDate("2016-06-01");
        phoneNumber.setEndDate("2016-09-01");
        phoneNumbers.add(phoneNumber);
        return phoneNumbers;
    }

    public static List<PhoneNumber> buildTestCaseE() {

        List<PhoneNumber> phoneNumbers = new ArrayList<>();
        PhoneNumber phoneNumber = new PhoneNumber();
        phoneNumber.setPhoneNumber("0987000011");
        phoneNumber.setStartDate("2016-03-01");
        phoneNumber.setEndDate("2016-05-01");
        phoneNumbers.add(phoneNumber);

        phoneNumber = new PhoneNumber();
        phoneNumber.setPhoneNumber("0987000001");
        phoneNumber.setStartDate("2016-01-01");
        phoneNumber.setEndDate("2016-03-01");
        phoneNumbers.add(phoneNumber);

        phoneNumber = new PhoneNumber();
        phoneNumber.setPhoneNumber("0987000006");
        phoneNumber.setStartDate("2016-12-01");
        phoneNumber.setEndDate(null);
        phoneNumbers.add(phoneNumber);

        phoneNumber = new PhoneNumber();
        phoneNumber.setPhoneNumber("0987000005");
        phoneNumber.setStartDate("2016-09-01");
        phoneNumber.setEndDate("2016-12-01");
        phoneNumbers.add(phoneNumber);

        phoneNumber = new PhoneNumber();
        phoneNumber.setPhoneNumber("0987000009");
        phoneNumber.setStartDate("2016-06-01");
        phoneNumber.setEndDate("2016-09-01");
        phoneNumbers.add(phoneNumber);
        return phoneNumbers;
    }


}
