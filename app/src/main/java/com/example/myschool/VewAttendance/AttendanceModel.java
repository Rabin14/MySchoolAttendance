package com.example.myschool.VewAttendance;

public class AttendanceModel {
    private String A_Date;
    private String B_Time;
    private String C_Class;
    private String D_Roll;
    private String E_Name;
    private String F_Status;

    public AttendanceModel() {

    }

    public AttendanceModel(String a_Date, String b_Time, String c_Class, String d_Roll, String e_Name, String f_Status) {
        A_Date = a_Date;
        B_Time = b_Time;
        C_Class = c_Class;
        D_Roll = d_Roll;
        E_Name = e_Name;
        F_Status = f_Status;
    }

    public String getA_Date() {
        return A_Date;
    }

    public void setA_Date(String a_Date) {
        A_Date = a_Date;
    }

    public String getB_Time() {
        return B_Time;
    }

    public void setB_Time(String b_Time) {
        B_Time = b_Time;
    }

    public String getC_Class() {
        return C_Class;
    }

    public void setC_Class(String c_Class) {
        C_Class = c_Class;
    }

    public String getD_Roll() {
        return D_Roll;
    }

    public void setD_Roll(String d_Roll) {
        D_Roll = d_Roll;
    }

    public String getE_Name() {
        return E_Name;
    }

    public void setE_Name(String e_Name) {
        E_Name = e_Name;
    }

    public String getF_Status() {
        return F_Status;
    }

    public void setF_Status(String f_Status) {
        F_Status = f_Status;
    }
}
