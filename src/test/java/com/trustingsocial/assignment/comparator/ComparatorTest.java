package com.trustingsocial.assignment.comparator;

import com.trustingsocial.assignment.TestCaseHelper;
import com.trustingsocial.assignment.model.PhoneNumber;
import org.hamcrest.MatcherAssert;
import org.hamcrest.collection.IsIterableContainingInOrder;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ComparatorTest {

    @Test
    public void sortByPhoneNumber() {
        List<PhoneNumber> phoneNumbers = TestCaseHelper.buildTestCaseE();
        Collections.sort(phoneNumbers, new SortingPhoneNumber());

        List<String> phones = phoneNumbers.stream().map(t -> t.getPhoneNumber()).collect(Collectors.toList());
        MatcherAssert.assertThat(phones, IsIterableContainingInOrder.contains("0987000001", "0987000005", "0987000006", "0987000009", "0987000011"));
    }

    @Test
    public void sortByStartDate() {
        List<PhoneNumber> phoneNumbers = TestCaseHelper.buildTestCaseE();
        Collections.sort(phoneNumbers, new SortingStartDate());

        List<String> startDates = phoneNumbers.stream().map(t -> t.getStartDate()).collect(Collectors.toList());
        MatcherAssert.assertThat(startDates, IsIterableContainingInOrder.contains("2016-01-01", "2016-03-01", "2016-06-01", "2016-09-01", "2016-12-01"));
    }
}
