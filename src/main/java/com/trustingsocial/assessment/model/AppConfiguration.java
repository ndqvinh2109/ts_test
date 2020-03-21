package com.trustingsocial.assessment.model;

public class AppConfiguration {

    private int total;
    private int buffer;
    private String input;
    private String output;
    private String sortedFile;
    private String tempFile;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getBuffer() {
        return buffer;
    }

    public void setBuffer(int buffer) {
        this.buffer = buffer;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getSortedFile() {
        return sortedFile;
    }

    public void setSortedFile(String sortedFile) {
        this.sortedFile = sortedFile;
    }

    public String getTempFile() {
        return tempFile;
    }

    public void setTempFile(String tempFile) {
        this.tempFile = tempFile;
    }

}
