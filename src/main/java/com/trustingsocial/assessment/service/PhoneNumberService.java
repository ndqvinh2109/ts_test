package com.trustingsocial.assessment.service;

import com.trustingsocial.assessment.PhoneNumberActivation;
import com.trustingsocial.assessment.comparator.SortingStartDate;
import com.trustingsocial.assessment.exception.ExternalSortingException;
import com.trustingsocial.assessment.model.AppConfiguration;
import com.trustingsocial.assessment.model.PhoneNumber;
import com.trustingsocial.assessment.util.ConversionHelper;
import com.trustingsocial.assessment.util.TimeMetric;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class PhoneNumberService {

    private static final Logger logger = LoggerFactory.getLogger(PhoneNumberService.class);

    private String inputSortedFile;
    private String outputFinal;

    private int total; // total items
    private int buffer; // max items the memory buffer can hold

    private TimeMetric timeMetric;

    {
        timeMetric = new TimeMetric("Phone Number Service");
    }

    public PhoneNumberService() {
    }

    public PhoneNumberService(AppConfiguration appConfiguration) {
        this.inputSortedFile = appConfiguration.getSortedFile();
        this.outputFinal = appConfiguration.getOutput();
        this.total = appConfiguration.getTotal();
        this.buffer = appConfiguration.getBuffer();
    }

    /**
     * split sorted input list into the chunk then find actual activation date
     * Time complexity: O (k*m) where k is numbers of temp files in maximum m buffer items
     * Sapce complexity: O(m + t) where m is the list of numbers in chunk file and t is the
     * map storing grouping by phone number => 0(m)
     *
     * @param files
     */
    public void findActivePhoneNumber(List<File> files) {

        try {
            Path path = Paths.get(inputSortedFile);

            try (BufferedReader br = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {

                int i, j;
                i = j = 0;

                // Iterate through the elements in the file

                for (i = 0; i < files.size(); i++) {
                    // Read M-element chunk at a time from the file
                    List<PhoneNumber> phoneNumbers = new ArrayList<>();

                    // O (buffer)
                    for (j = 0; j < (buffer < total ? buffer : total); j++) {
                        String t = br.readLine();

                        if (t != null) {
                            String[] array = t.split(",");
                            phoneNumbers.add(ConversionHelper.to(array));
                        } else {
                            break;
                        }
                    }

                    Map<String, List<PhoneNumber>> map = groupingByPhoneNumber(phoneNumbers);

                    File outputFile = Paths.get(outputFinal).toFile();

                    // O(t)
                    for (Map.Entry<String, List<PhoneNumber>> t : map.entrySet()) {
                        List<PhoneNumber> phoneNumberList = t.getValue();
                        // sort by start date
                        Collections.sort(phoneNumberList, new SortingStartDate());
                        map.put(t.getKey(), phoneNumberList);
                    }

                    try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile, true))) {

                        // O(t * z) where t is map grouping phone numbers in grouped k sorted list phone numbers
                        for (Map.Entry<String, List<PhoneNumber>> t : map.entrySet()) {

                            List<PhoneNumber> phoneNumberStored = t.getValue();
                            StringBuilder stringBuilder = findActivatePhoneNumber(phoneNumberStored);

                            try {
                                if (!stringBuilder.toString().isEmpty()) {
                                    bw.write(stringBuilder.toString());
                                    bw.newLine();
                                }
                            } catch (IOException e) {
                                throw new ExternalSortingException(e.getMessage(), e);
                            }
                        }

                    } catch (IOException e) {
                        throw new ExternalSortingException(e.getMessage(), e);
                    }

                }
            } catch (NoSuchFileException e) {
                throw new ExternalSortingException("File not Found!", e);
            } catch (Exception e) {
                throw new ExternalSortingException("Unexpected error occured!", e);
            }
            print();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public StringBuilder findActivatePhoneNumber(List<PhoneNumber> phoneNumberList) {
        StringBuilder stringBuilder = new StringBuilder();

        PhoneNumber lastPhoneNumber = phoneNumberList.get(phoneNumberList.size() - 1);

        if (lastPhoneNumber.getEndDate() == null) {

            if (phoneNumberList.size() == 1) {
                stringBuilder = ConversionHelper.buildActivationPhone(phoneNumberList.get(0));
            } else if (phoneNumberList.size() >= 2) {

                PhoneNumber foundPhoneNumber = null;
                for (int z = 0; z < phoneNumberList.size() - 1; z++) {
                    PhoneNumber endDate = phoneNumberList.get(z);
                    PhoneNumber startDate = phoneNumberList.get(z + 1);

                    if (endDate.getEndDate() != null && !endDate.getEndDate().equalsIgnoreCase(startDate.getStartDate())) {
                        foundPhoneNumber = startDate;
                    }
                }

                if (foundPhoneNumber != null) {
                    stringBuilder = ConversionHelper.buildActivationPhone(foundPhoneNumber);
                } else {
                    PhoneNumber firstPhoneNumber = phoneNumberList.get(0);
                    stringBuilder = ConversionHelper.buildActivationPhone(firstPhoneNumber);
                }
            }
        }

        return stringBuilder;
    }

    private Map<String, List<PhoneNumber>> groupingByPhoneNumber(List<PhoneNumber> phoneNumbers) {
        return phoneNumbers.stream().
                collect(Collectors.groupingBy(PhoneNumber::getPhoneNumber,
                        LinkedHashMap::new, Collectors.toList()));
    }

    private void print() {
        timeMetric.print();
    }

}
