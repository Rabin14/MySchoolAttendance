package com.example.myschool.Students;

public class StudentModel {
    String NAME,ROLL,ID_NO,S_CLASS,MOBILE;

    public StudentModel() {
    }

    public StudentModel(String NAME, String ROLL, String ID_NO, String s_CLASS, String MOBILE) {
        this.NAME = NAME;
        this.ROLL = ROLL;
        this.ID_NO = ID_NO;
        S_CLASS = s_CLASS;
        this.MOBILE = MOBILE;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getROLL() {
        return ROLL;
    }

    public void setROLL(String ROLL) {
        this.ROLL = ROLL;
    }

    public String getID_NO() {
        return ID_NO;
    }

    public void setID_NO(String ID_NO) {
        this.ID_NO = ID_NO;
    }

    public String getS_CLASS() {
        return S_CLASS;
    }

    public void setS_CLASS(String s_CLASS) {
        S_CLASS = s_CLASS;
    }

    public String getMOBILE() {
        return MOBILE;
    }

    public void setMOBILE(String MOBILE) {
        this.MOBILE = MOBILE;
    }
}
