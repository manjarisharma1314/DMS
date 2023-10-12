package com.amdocs.doctor.main;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.amdocs.doctor.dao.DoctorDataAccess;
import com.amdocs.doctor.model.DoctorRecord;

public class DoctorManagementSystem {
    
    public static void main(String[] args) throws SQLException {
        Scanner inputScanner = new Scanner(System.in);
        boolean continueRunning = true;
        
        do {
            System.out.println("Welcome to Doctor Management System");
            System.out.println("1. Add New Doctor");
            System.out.println("2. Update Doctor Fees");
            System.out.println("3. Delete Doctor");
            System.out.println("4. View All Doctors");
            System.out.println("5. Find Doctors by Specialization");
            System.out.println("6. Find Doctors with Fees Lower Than All Doctors in a Given Shift");
            System.out.println("7. Count Number of Doctors by Shift");
            System.out.println("8. Exit");
            
            System.out.println("Enter your option: ");
            int userChoice = inputScanner.nextInt();
            
            switch (userChoice) {
                case 1: {
                    System.out.println("Enter Doctor's Name:");
                    String name = inputScanner.next();
                    
                    System.out.println("Enter Mobile Number:");
                    String mobileNumber = inputScanner.next();
                    
                    System.out.println("Enter Specialization:");
                    String specialization = inputScanner.next();
                    
                    System.out.println("Enter Available Shift:");
                    String shift = inputScanner.next();
                    
                    System.out.println("Enter Fees:");
                    double fees = inputScanner.nextDouble();
                    
                    DoctorRecord newDoctor = new DoctorRecord(name, mobileNumber, specialization, shift, fees);
                    
                    try {
                        int result = DoctorDataAccess.addDoctorRecord(newDoctor);
                        if (result > 0) {
                            System.out.println("Doctor details inserted successfully.");
                        } else {
                            System.out.println("Insertion failed.");
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                break;
                
                case 2: {
                    System.out.println("Enter Doctor ID:");
                    int id = inputScanner.nextInt();
                    
                    System.out.println("Enter New Fee Amount:");
                    double newFee = inputScanner.nextDouble();
                    
                    boolean isUpdated = DoctorDataAccess.updateDoctorFees(id, newFee);
                    if (isUpdated) {
                        System.out.println("Fees updated successfully");
                    } else {
                        System.out.println("Unable to update fees.");
                    }
                }
                break;
                
                case 3: {
                    System.out.println("Enter Doctor ID to delete the doctor: ");
                    int doctorIdToDelete = inputScanner.nextInt();
                    int deleteResult = DoctorDataAccess.deleteDoctor(doctorIdToDelete);
                    
                    if (deleteResult > 0) {
                        System.out.println("Successfully Deleted");
                    } else {
                        System.out.println("Unable to delete the doctor.");
                    }
                }
                break;
                
                case 4: {
                    System.out.println("All Doctor Details");
                    List<DoctorRecord> doctors = DoctorDataAccess.showAllDoctors();
                    for (DoctorRecord doctor : doctors) {
                        System.out.println(doctor.toString());
                    }
                }
                break;
                
                case 5: {
                    System.out.println("Enter Specialization:");
                    String specializationToFind = inputScanner.next();
                    List<DoctorRecord> doctors = DoctorDataAccess.searchBySpecialization(specializationToFind);
                    for (DoctorRecord doctor : doctors) {
                        System.out.println(doctor.toString());
                    }
                }
                break;
                
                case 6: {
                    System.out.println("Enter Shift:");
                    String shiftToFind = inputScanner.next();
                    List<DoctorRecord> doctors = DoctorDataAccess.searchByFeesAndShift(shiftToFind);
                    for (DoctorRecord doctor : doctors) {
                        System.out.println(doctor.toString());
                    }
                }
                break;
                
                case 7: {
                    System.out.println("Enter the Available Shift (morning/evening):");
                    String shiftToCount = inputScanner.next();
                    int count = DoctorDataAccess.countDoctorsByShift(shiftToCount);
                    System.out.println(count + " Doctors Available in " + shiftToCount + " shift");
                }
                break;
                
                case 8: {
                    System.out.println("Thank you ");
                    inputScanner.close();
                    System.exit(0);
                }
                break;
            }
        } while (true);
    }
}
