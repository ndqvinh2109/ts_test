package com.trustingsocial.assessment;

import com.trustingsocial.assessment.model.AppConfiguration;
import com.trustingsocial.assessment.service.FileSorter;
import com.trustingsocial.assessment.service.FileSplitter;
import com.trustingsocial.assessment.service.PhoneNumberService;
import com.trustingsocial.assessment.util.TimeMetric;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class PhoneNumberActivation {

    private static final Logger logger = LoggerFactory.getLogger(PhoneNumberActivation.class);

    public static void findActualActivationDate(AppConfiguration appConfiguration) {

        logger.info("----------------------Running Application!----------------------");
        TimeMetric timeMetric = new TimeMetric("Find the actual activation date");

        try {

            FileSplitter fileSplitter = new FileSplitter(appConfiguration);
            Map map = fileSplitter.split();

            FileSorter fileSorter = new FileSorter(appConfiguration);
            fileSorter.sort(new ArrayList<>(map.values()));

            PhoneNumberService phoneNumberService = new PhoneNumberService(appConfiguration);
            phoneNumberService.findActivePhoneNumber(new ArrayList<>(map.values()));


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        timeMetric.print();
        logger.info("----------------------Successfully executed!--------------------");

    }

}
