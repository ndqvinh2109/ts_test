package com.trustingsocial.assignment.task1.service;

import com.trustingsocial.assignment.task1.model.AppConfiguration;
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
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class FileSorterTest {

    private FileSorter fileSorter;
    private FileSplitter fileSplitter;
    private AppConfiguration appConfiguration;

    private String testFilename = "test.txt";
    private Path tempDirWithPrefix;
    private Map<String, File> files;

    @BeforeEach
    public void setUp() throws IOException {

        tempDirWithPrefix = Files.createTempDirectory("temp");

        appConfiguration = new AppConfiguration();
        appConfiguration.setInput(tempDirWithPrefix.toString() + File.separator + testFilename);
        appConfiguration.setTotal(9);
        appConfiguration.setBuffer(3);

        fileSorter = new FileSorter(appConfiguration);
        fileSplitter = new FileSplitter(appConfiguration);

    }

    @AfterEach
    public void tearDown() throws IOException {
        String[] testFiles = {testFilename};
        for (String file : testFiles) {
            Files.deleteIfExists(Paths.get(tempDirWithPrefix.toString(), file));
        }

        for (File file : new ArrayList<>(files.values())) {
            file.delete();
        }
    }

    @Test
    public void testCompareAndMerge() throws IOException {
        writeFile();
        files = fileSplitter.split();
        String sorted = fileSorter.sort(new ArrayList<>(files.values()));

        assertThat(files, IsNull.notNullValue());
        assertThat(3, is(files.size()));

        File file = new File(sorted + File.separator + "sorted.txt");
        assertThat(file.exists(), Is.is(Boolean.TRUE));

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

        File file = Paths.get(tempDirWithPrefix.toString(), testFilename).toFile();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, false))) {
            bw.write(fileContent);
            bw.newLine();
            bw.flush();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }
}
