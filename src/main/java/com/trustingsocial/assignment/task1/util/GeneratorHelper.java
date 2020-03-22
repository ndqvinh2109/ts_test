package com.trustingsocial.assignment.task1.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

public class GeneratorHelper {

    public static void generate(int n) {
        BigDecimal number1 = new BigDecimal("0987000001");

        String fileName = "C:\\Users\\fpt-vnguyend\\Desktop\\t\\test_hard.txt";

        try {
            FileWriter fw = new FileWriter(fileName);
            PrintWriter pw = new PrintWriter(fw);

            for (int i = 0; i < 50000000; i++) {
                String str = String.valueOf(number1.add(new BigDecimal(i))) + ",2016-12-01,";
                pw.println(str);
            }
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
