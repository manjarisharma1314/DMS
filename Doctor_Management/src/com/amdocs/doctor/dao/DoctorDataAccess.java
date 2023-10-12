package com.amdocs.doctor.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.amdocs.doctor.model.DoctorRecord;

public class DoctorDataAccess {

    public static int addDoctorRecord(DoctorRecord doctor) throws SQLException {
        Connection con = DatabaseConnection.getConnection();
        PreparedStatement Statement = con.prepareStatement("INSERT INTO DoctorRecords (DoctorID, DoctorName, MobileNumber, Specialization, AvailableShift, Fees) VALUES (seq.nextval, ?, ?, ?, ?, ?)");

        Statement.setString(1, doctor.getDoctorName());
        Statement.setString(2, doctor.getMobileNumber());
        Statement.setString(3, doctor.getSpecialization());
        Statement.setString(4, doctor.getAvailableShift());
        Statement.setDouble(5, doctor.getFees());

        int rowsAffected = Statement.executeUpdate();

        Statement.close();
        return rowsAffected;
    }

    public static boolean updateDoctorFees(int id, double newFee) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE DoctorRecords SET Fees = ? WHERE DoctorID = ?");

        preparedStatement.setDouble(1, newFee);
        preparedStatement.setInt(2, id);

        int status = preparedStatement.executeUpdate();

        preparedStatement.close();
        connection.close();

        return status > 0;
    }

    public static int deleteDoctor(int id) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM DoctorRecords WHERE DoctorID = ?");

        preparedStatement.setInt(1, id);

        int status = preparedStatement.executeUpdate();

        preparedStatement.close();
        connection.close();

        return status;
    }

    public static List<DoctorRecord> showAllDoctors() {
        List<DoctorRecord> doctorsList = new ArrayList<>();

        try {
            Connection connection = DatabaseConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM DoctorRecords");

            while (resultSet.next()) {
                DoctorRecord doctor = new DoctorRecord(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getDouble(6));
                doctorsList.add(doctor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return doctorsList;
    }

    public static List<DoctorRecord> searchBySpecialization(String specialization) {
        List<DoctorRecord> doctorsList = new ArrayList<>();
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM DoctorRecords WHERE Specialization = ?");
            preparedStatement.setString(1, specialization);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                DoctorRecord doctor = new DoctorRecord(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getDouble(6));
                doctorsList.add(doctor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return doctorsList;
    }

    public static List<DoctorRecord> searchByFeesAndShift(String shift) {
        List<DoctorRecord> doctorsList = new ArrayList<>();
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM DoctorRecords WHERE Fees < (SELECT MIN(Fees) FROM DoctorRecords WHERE AvailableShift LIKE ?)");
            preparedStatement.setString(1, shift);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                DoctorRecord doctor = new DoctorRecord(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getDouble(6));
                doctorsList.add(doctor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return doctorsList;
    }

    public static int countDoctorsByShift(String shift) {
        int result = 0;
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(*) FROM DoctorRecords WHERE AvailableShift = ?");
            preparedStatement.setString(1, shift);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                result = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
}
