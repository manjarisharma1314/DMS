package com.amdocs.doctor.model;

public class DoctorRecord {

    private int doctorID;
    private String doctorName;
    private String mobileNumber;
    private String specialization;
    private String availableShift;
    private double fees;

    public DoctorRecord() {
    }

    public DoctorRecord(String doctorName, String mobileNumber, String specialization, String availableShift, double fees) {
        this.doctorName = doctorName;
        this.mobileNumber = mobileNumber;
        this.specialization = specialization;
        this.availableShift = availableShift;
        
        this.fees = fees;
    }

    public DoctorRecord(int doctorID, String doctorName, String mobileNumber, String specialization, String availableShift, double fees) {
        this.doctorID = doctorID;
        this.doctorName = doctorName;
        this.mobileNumber = mobileNumber;
        this.specialization = specialization;
        this.availableShift = availableShift;
        this.fees = fees;
    }

    public int getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(int doctorID) {
        this.doctorID = doctorID;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getAvailableShift() {
        return availableShift;
    }

    public void setAvailableShift(String availableShift) {
        this.availableShift = availableShift;
    }

    public double getFees() {
        return fees;
    }

    public void setFees(double fees) {
        this.fees = fees;
    }

    @Override
    public String toString() {
        return "DoctorRecord [doctorID=" + doctorID + ", doctorName=" + doctorName + ", mobileNumber=" + mobileNumber
                + ", specialization=" + specialization + ", availableShift=" + availableShift + ", fees=" + fees + "]";
    }
}
