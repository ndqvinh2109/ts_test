package com.trustingsocial.assessment.service;

import com.trustingsocial.assessment.model.AppConfiguration;
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

public class FileSorterTest {

    private FileSorter fileSorter;
    private FileSplitter fileSplitter;
    private AppConfiguration appConfiguration;

    private String tempFilesDir = "C:\\Users\\fpt-vnguyend\\Desktop\\t\\test\\";
    private String testFilename = "test.txt";
    private String tempFile = "C:\\Users\\fpt-vnguyend\\Desktop\\t\\test\\temp-file-";
    private String inputSortedFile = "C:\\Users\\fpt-vnguyend\\Desktop\\t\\test\\sorted_file.txt";

    @BeforeEach
    public void setUp() {

        appConfiguration = new AppConfiguration();
        appConfiguration.setTempFile(tempFile);
        appConfiguration.setSortedFile(inputSortedFile);
        appConfiguration.setInput(tempFilesDir + testFilename);
        appConfiguration.setTotal(9);
        appConfiguration.setBuffer(3);

        fileSorter = new FileSorter(appConfiguration);
        fileSplitter = new FileSplitter(appConfiguration);
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
        fileSorter.sort(new ArrayList<>(files.values()));

        assertThat(files, IsNull.notNullValue());
        assertThat(3, is(files.size()));
        assertThat(files.keySet(), hasItem("C:\\Users\\fpt-vnguyend\\Desktop\\t\\test\\temp-file-0.txt"));

        File aFile = new File("C:\\Users\\fpt-vnguyend\\Desktop\\t\\test\\", "sorted_file.txt");
        assertThat(aFile.exists(), Is.is(Boolean.TRUE));
        assertThat(aFile.length(), Is.is(286l));

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
