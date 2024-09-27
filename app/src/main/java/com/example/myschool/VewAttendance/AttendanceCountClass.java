package com.example.myschool.VewAttendance;

public class AttendanceCountClass {
    String ONE,TWO,THREE,FOUR,PP,TOTAL,SL_NO;

    public AttendanceCountClass() {
    }

    public AttendanceCountClass(String ONE, String TWO, String THREE, String FOUR, String PP, String TOTAL, String SL_NO) {
        this.ONE = ONE;
        this.TWO = TWO;
        this.THREE = THREE;
        this.FOUR = FOUR;
        this.PP = PP;
        this.TOTAL = TOTAL;
        this.SL_NO = SL_NO;
    }

    public String getONE() {
        return ONE;
    }

    public void setONE(String ONE) {
        this.ONE = ONE;
    }

    public String getTWO() {
        return TWO;
    }

    public void setTWO(String TWO) {
        this.TWO = TWO;
    }

    public String getTHREE() {
        return THREE;
    }

    public void setTHREE(String THREE) {
        this.THREE = THREE;
    }

    public String getFOUR() {
        return FOUR;
    }

    public void setFOUR(String FOUR) {
        this.FOUR = FOUR;
    }

    public String getPP() {
        return PP;
    }

    public void setPP(String PP) {
        this.PP = PP;
    }

    public String getTOTAL() {
        return TOTAL;
    }

    public void setTOTAL(String TOTAL) {
        this.TOTAL = TOTAL;
    }

    public String getSL_NO() {
        return SL_NO;
    }

    public void setSL_NO(String SL_NO) {
        this.SL_NO = SL_NO;
    }
}
