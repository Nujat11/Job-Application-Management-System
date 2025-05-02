package com.example.jobapplicationmanagementsystem;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class JobApplication implements Serializable {

    private int applicationNo;
    private String name;
    private String designationAppliedFor;
    private LocalDate dateOfApplication;
    private boolean isFreshGraduate;
    private int yearOfExperience;
    private ArrayList<String> experiseList;

    public JobApplication() {
    }

    public JobApplication(int applicationNo, String name, String designationAppliedFor, LocalDate dateOfApplication, boolean isFreshGraduate, int yearOfExperience, ArrayList<String> experiseList) {
        this.applicationNo = applicationNo;
        this.name = name;
        this.designationAppliedFor = designationAppliedFor;
        this.dateOfApplication = dateOfApplication;
        this.isFreshGraduate = isFreshGraduate;
        this.yearOfExperience = yearOfExperience;
        this.experiseList = experiseList;
    }

    public int getApplicationNo() {
        return applicationNo;
    }

    public void setApplicationNo(int applicationNo) {
        this.applicationNo = applicationNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesignationAppliedFor() {
        return designationAppliedFor;
    }

    public void setDesignationAppliedFor(String designationAppliedFor) {
        this.designationAppliedFor = designationAppliedFor;
    }

    public LocalDate getDateOfApplication() {
        return dateOfApplication;
    }

    public void setDateOfApplication(LocalDate dateOfApplication) {
        this.dateOfApplication = dateOfApplication;
    }

    public boolean isFreshGraduate() {
        return isFreshGraduate;
    }

    public void setFreshGraduate(boolean freshGraduate) {
        isFreshGraduate = freshGraduate;
    }

    public int getYearOfExperience() {
        return yearOfExperience;
    }

    public void setYearOfExperience(int yearOfExperience) {
        this.yearOfExperience = yearOfExperience;
    }

    public ArrayList<String> getExperiseList() {
        return experiseList;
    }

    public void setExperiseList(ArrayList<String> experiseList) {
        this.experiseList = experiseList;
    }

    @Override
    public String toString() {
        return "JobApplication{" +
                "applicationNo=" + applicationNo +
                ", name='" + name + '\'' +
                ", designationAppliedFor='" + designationAppliedFor + '\'' +
                ", dateOfApplication=" + dateOfApplication +
                ", isFreshGraduate=" + isFreshGraduate +
                ", yearOfExperience=" + yearOfExperience +
                ", experiseList=" + experiseList +
                '}';
    }

    public void showApplicationDetailAsAlert(){


    }


}
