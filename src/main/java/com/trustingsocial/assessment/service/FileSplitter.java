package com.trustingsocial.assessment.service;

import com.trustingsocial.assessment.comparator.SortingPhoneNumber;
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
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class FileSplitter {

    private static final Logger logger = LoggerFactory.getLogger(FileSplitter.class);

    private String input;
    private String tempFile;
    private int total; // total items
    private int bufferItem; // max items the memory buffer can hold
    private int slices;

    private static long count = 0;
    Map<String, File> returnedMap = new HashMap<>();

    private TimeMetric timeMetric;

    {
        timeMetric = new TimeMetric("File Splitter");
    }

    public FileSplitter(AppConfiguration appConfiguration) {
        this.input = appConfiguration.getInput();
        this.tempFile = appConfiguration.getTempFile();
        this.total = appConfiguration.getTotal();
        this.bufferItem = appConfiguration.getBuffer();
        this.slices = (int) Math.ceil((double) total / bufferItem);
    }


    /**
     * Split unsorted file to multiple sorted temp files
     * Time complexity: 0 (k*m*log(m)) where k is numbers of temp files in maximum m buffer items
     * Space complexity: O (m) where m is maximum bufer items
     *
     * @return
     */
    public Map<String, File> split() {

        PhoneNumber[] buffer = new PhoneNumber[this.bufferItem < total ? this.bufferItem : total];

        int i = 0;
        int j = 0;

        Path path = Paths.get(input);
        try (BufferedReader br = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {

            for (i = 0; i < slices; i++) {
                // Read M-element chunk at a time from the file O(buffer)
                for (j = 0; j < (bufferItem < total ? bufferItem : total); j++) {
                    ++count;
                    String t = br.readLine();

                    if (t != null) {
                        String[] array = t.split(",");
                        buffer[j] = ConversionHelper.to(array);
                    } else {
                        break;
                    }
                }

                // Sort elements of buffer O(buffer*log(buffer))
                Arrays.sort(buffer, new SortingPhoneNumber());

                String tempFilename = tempFile + Integer.toString(i) + ".txt";
                File temp = Paths.get(tempFilename).toFile();

                // O(buffer)
                try (BufferedWriter bw = new BufferedWriter(new FileWriter(temp, false))) {
                    for (int k = 0; k < j; k++) {
                        bw.write(ConversionHelper.from(buffer[k]).toString());
                        if (k + 1 < j) {
                            bw.newLine();
                        }
                    }
                } catch (IOException e) {
                    throw new ExternalSortingException(e.getMessage(), e);
                }

                returnedMap.put(tempFilename, temp);
            }

        } catch (NoSuchFileException e) {
            throw new ExternalSortingException("File not Found!", e);
        } catch (Exception e) {
            throw new ExternalSortingException("Unexpected error occured!", e);
        }

        print();
        return returnedMap;
    }

    private void print() {
        timeMetric.print();
        logger.info("Lines processed: " + count);
        logger.info("Number of temp files: " + returnedMap.size());
    }

}
