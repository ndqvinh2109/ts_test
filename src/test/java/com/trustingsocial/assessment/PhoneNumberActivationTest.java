package com.trustingsocial.assessment;

import com.trustingsocial.assessment.model.AppConfiguration;
import com.trustingsocial.assessment.service.FileSorter;
import com.trustingsocial.assessment.service.FileSplitter;
import com.trustingsocial.assessment.service.PhoneNumberService;
import org.hamcrest.core.Is;
import org.hamcrest.core.IsNull;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class PhoneNumberActivationTest {

    private FileSorter fileSorter;
    private FileSplitter fileSplitter;
    private PhoneNumberService phoneNumberService;
    private AppConfiguration appConfiguration;

    private String tempFilesDir = "C:\\Users\\fpt-vnguyend\\Desktop\\t\\test\\";
    private String testFilename = "test.txt";
    private String tempFile = "C:\\Users\\fpt-vnguyend\\Desktop\\t\\test\\temp-file-";
    private String inputSortedFile = "C:\\Users\\fpt-vnguyend\\Desktop\\t\\test\\sorted_file.txt";
    private String activationNumber = "C:\\Users\\fpt-vnguyend\\Desktop\\t\\test\\activation_number.txt";

    @BeforeEach
    public void setUp() {

        appConfiguration = new AppConfiguration();
        appConfiguration.setTempFile(tempFile);
        appConfiguration.setSortedFile(inputSortedFile);
        appConfiguration.setInput(tempFilesDir + testFilename);
        appConfiguration.setTotal(9);
        appConfiguration.setBuffer(3);
        appConfiguration.setOutput(activationNumber);

        fileSorter = new FileSorter(appConfiguration);
        fileSplitter = new FileSplitter(appConfiguration);
        phoneNumberService = new PhoneNumberService(appConfiguration);
    }

    @AfterEach
    public void tearDown() throws IOException {
        String[] testFiles = {"temp-file-0.txt", "temp-file-1.txt", "temp-file-2.txt", testFilename, "sorted_file.txt"};
        for (String file : testFiles) {
            Files.deleteIfExists(Paths.get(tempFilesDir, file));
        }
    }

    @Test
    public void testCompareAndMerge() throws IOException {
        writeFile();
        Map<String, File> files = fileSplitter.split();
        String sorted = fileSorter.sort(new ArrayList<>(files.values()));
        phoneNumberService.findActivePhoneNumber(new ArrayList<>(files.values()), sorted);

        assertThat(files, IsNull.notNullValue());
        assertThat(3, is(files.size()));


    }

    private void writeFile() {

        String fileContent = "0987000001,2016-03-01,2016-05-01\n" +
                "0987000002,2016-02-01,2016-03-01\n" +
                "0987000001,2016-01-01,2016-03-01\n" +
                "0987000001,2016-12-01,\n" +
                "0987000002,2016-03-01,2016-05-01\n" +
                "0987000003,2016-01-01,2016-01-10\n" +
                "0987000001,2016-09-01,2016-12-01\n" +
                "0987000002,2016-05-01,\n" +
                "0987000001,2016-06-01,2016-09-01";

        File file = Paths.get(tempFilesDir, testFilename).toFile();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, false))) {
            bw.write(fileContent);
            bw.newLine();
            bw.flush();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }
}
