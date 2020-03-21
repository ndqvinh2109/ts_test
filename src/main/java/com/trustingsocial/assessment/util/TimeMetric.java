package com.trustingsocial.assessment.util;

import com.trustingsocial.assessment.AssessmentApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimeMetric {

    private static final Logger logger = LoggerFactory.getLogger(TimeMetric.class);

    private String name;
    private Long start;

    public TimeMetric(String name) {
        this.name = name;
        this.start = System.currentTimeMillis();
    }

    public void print() {
        Long end = System.currentTimeMillis();
        logger.info(name + " took " + (end - start) + " ms.");
    }

}
