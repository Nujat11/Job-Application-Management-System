package com.example.jobapplicationmanagementsystem;

public class DummyTableViewClass {
    private String toStringVal;

    public DummyTableViewClass() {
    }

    public DummyTableViewClass(String toStringVal) {
        this.toStringVal = toStringVal;
    }

    public String getToStringVal() {
        return toStringVal;
    }

    public void setToStringVal(String toStringVal) {
        this.toStringVal = toStringVal;
    }

    @Override
    public String toString() {
        return "DummyTableViewClass{" +
                "toStringVal='" + toStringVal + '\'' +
                '}';
    }
}
