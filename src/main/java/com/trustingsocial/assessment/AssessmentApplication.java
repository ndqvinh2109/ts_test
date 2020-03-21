package com.trustingsocial.assessment;

import com.trustingsocial.assessment.model.AppConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
public class AssessmentApplication implements CommandLineRunner {

    @Value("${ts.file.total}")
    private int total;

    @Value("${ts.file.buffer}")
    private int buffer;

    @Value("${ts.file.input}")
    private String input;

    @Value("${ts.file.output}")
    private String output;

    private static final Logger logger = LoggerFactory.getLogger(AssessmentApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(AssessmentApplication.class, args);
    }


    public AppConfiguration appConfiguration() {
        AppConfiguration appConfiguration = new AppConfiguration();
        appConfiguration.setTotal(total);
        appConfiguration.setBuffer(buffer);
        appConfiguration.setInput(input);
        appConfiguration.setOutput(output);
        return appConfiguration;
    }

    @Override
    public void run(String... args) {

        logger.info("Total items: " + total);
        logger.info("Input file path: " + input);
        logger.info("Output file path: " + output);
        logger.info("Buffer items: " + buffer);

        logger.info("EXECUTING : command line runner");

        PhoneNumberActivation.findActualActivationDate(appConfiguration());
    }


}
