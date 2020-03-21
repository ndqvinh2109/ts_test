package com.trustingsocial.assessment.service;

import com.trustingsocial.assessment.model.AppConfiguration;
import com.trustingsocial.assessment.model.PhoneNumber;
import com.trustingsocial.assessment.util.ConversionHelper;
import com.trustingsocial.assessment.util.TimeMetric;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.List;

public class FileSorter {

    private static final Logger logger = LoggerFactory.getLogger(FileSorter.class);

    private String inputSortedFile;
    private int total;
    private PhoneNumber[] topNums;
    private BufferedReader[] brs;

    private TimeMetric timeMetric;

    {
        timeMetric = new TimeMetric("File Sorter");
    }

    public void sort(List<File> files) throws IOException {
        init(files);
        compareAndMerge(files);
        print();
    }

    public FileSorter(AppConfiguration appConfiguration) {
        this.inputSortedFile = appConfiguration.getSortedFile();
        this.total = appConfiguration.getTotal();
    }


    /**
     * Merge K sorted temp files list
     * Time complexity: Big O: N * log K where N is total number of elements in all K temp files
     * Space complexity: O(K) min heap will be storing one number from all K temp files
     *
     * @param files
     * @throws IOException
     */
    private void compareAndMerge(List<File> files) throws IOException {
        FileWriter fw = new FileWriter(inputSortedFile);
        PrintWriter pw = new PrintWriter(fw);

        for (int i = 0; i < total; i++) {

            PhoneNumber min = topNums[0];
            int minFile = 0;

            // K-merge sort to maintain min heap
            for (int j = 0; j < files.size(); j++) {
                if (min.getPhoneNumber().compareTo(topNums[j].getPhoneNumber()) > 0) {
                    min = topNums[j];
                    minFile = j;
                }
            }

            pw.println(ConversionHelper.from(min).toString());

            String line = brs[minFile].readLine();
            PhoneNumber phoneNumber = new PhoneNumber();

            if (line != null) {
                String[] array = line.split(",");
                topNums[minFile] = ConversionHelper.to(array);
            } else {
                phoneNumber.setPhoneNumber("9999999999999999");
                topNums[minFile] = phoneNumber;
            }
        }

        for (int i = 0; i < files.size(); i++) {
            brs[i].close();
        }

        pw.close();
        fw.close();

    }

    private void init(List<File> files) throws IOException {
        topNums = new PhoneNumber[files.size()];
        brs = new BufferedReader[files.size()];

        // Pickup Min heap of each temp file
        for (int i = 0; i < files.size(); i++) {
            brs[i] = new BufferedReader(new FileReader(files.get(i)));

            String t = brs[i].readLine();

            String[] array = t.split(",");
            PhoneNumber phoneNumber = ConversionHelper.to(array);

            if (t != null) {
                topNums[i] = phoneNumber;
            } else {
                // read to the end of the temp file, assign large value to current min-heap, pickup number in the next file
                phoneNumber.setPhoneNumber("9999999999999999");
                topNums[i] = phoneNumber;
            }
        }
    }

    private void print() {
        timeMetric.print();
    }


}
