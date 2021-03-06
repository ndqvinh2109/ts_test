package com.trustingsocial.assignment.task1;

import com.trustingsocial.assignment.task1.exception.ActivationPhoneNumberException;
import com.trustingsocial.assignment.task1.model.AppConfiguration;
import com.trustingsocial.assignment.task1.service.FileSorter;
import com.trustingsocial.assignment.task1.service.FileSplitter;
import com.trustingsocial.assignment.task1.service.PhoneNumberService;
import com.trustingsocial.assignment.task1.util.TimeMetric;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PhoneNumberActivation {

    private static final Logger logger = LoggerFactory.getLogger(PhoneNumberActivation.class);

    public static void findActualActivationDate(AppConfiguration appConfiguration) {

        logger.info("----------------------Running Application!----------------------");
        TimeMetric timeMetric = new TimeMetric("Find the actual activation date");

        try {

            FileSplitter fileSplitter = new FileSplitter(appConfiguration);
            Map map = fileSplitter.split();

            List<File> fileList = new ArrayList<>(map.values());

            FileSorter fileSorter = new FileSorter(appConfiguration);
            String sortedPath = fileSorter.sort(fileList);

            PhoneNumberService phoneNumberService = new PhoneNumberService(appConfiguration);
            phoneNumberService.findActivePhoneNumber(fileList, sortedPath);

            deleteFiles(fileList);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        timeMetric.print();
        logger.info("----------------------Successfully executed!--------------------");

    }

    public static void isValid(String file) {
        File input = new File(file);
        if (!input.exists()) {
            throw new ActivationPhoneNumberException("The file do not exist. Path: " + input.getAbsolutePath(), null);
        }
    }

    private static void deleteFiles(List<File> fileList) {
        for (File file : fileList) {
            file.delete();
        }
    }

}
