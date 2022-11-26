package io.gynacare.gynacare.patients;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PatientsDAO {

    PreparedStatement cst = null;

    Connection conn = null;
    @Autowired
    DataSource dataSource;
    // get patients

public List<PatientsBean> fetchPatients() throws SQLException {
    String sqlQuery = "SELECT * FROM patient join login on patient.patient_login_id = login.login_id;";
    List<PatientsBean> patients = new ArrayList<>();
    try {
      this.conn = this.dataSource.getConnection();
      this.cst = this.conn.prepareStatement(sqlQuery);
      ResultSet rs = this.cst.executeQuery();
      while (rs.next()) {
        PatientsBean fetch = new PatientsBean();
        fetch.setPatientId(rs.getBigDecimal("patient_id"));
        fetch.setPatientFullName(rs.getString("patient_full_name"));
        fetch.setPatientEmail(rs.getString("patient_email"));
        fetch.setPatientLoginId(rs.getBigDecimal("patient_login_id"));
        fetch.setPatientWeight(rs.getBigDecimal("patient_weight"));
        fetch.setPatientAge(rs.getBigDecimal("patient_age"));
        fetch.setPatientGender(rs.getString("patient_gender"));
        fetch.setPatientMobile(rs.getString("patient_mobile"));
        patients.add(fetch);
      } 
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      this.conn.close();
    }  
    return patients; 
    
  }
   //getting the next primary key 
   public int getNextPrimaryKey() throws SQLException {
    String column_name = "patient_id";
    String table_name = "patient";
    String sql = "SELECT MAX(" + column_name + ") FROM " + table_name;
    int primary = 0;
    try {
      this.conn = this.dataSource.getConnection();
      this.cst = this.conn.prepareStatement(sql);
      ResultSet rs = this.cst.executeQuery();
      while (rs.next()) {
        primary = rs.getInt(1);
        primary++;
      } 
    } catch (Exception e) { 
      e.printStackTrace();
    } finally {
      this.conn.close();
    } 
    return primary;
  }
  
	  //create
	  public List<PatientsBean> createPatient(PatientsBean patientsBean) throws SQLException {
      String sql = "INSERT INTO patient(patient_id, patient_full_name, patient_email, patient_login_id,patient_weight,patient_age,patient_gender,patient_mobile) VALUES ( "+ getNextPrimaryKey() +",'"+ patientsBean.getPatientFullName()+"','"+ patientsBean.getPatientEmail()+"','"+ patientsBean.getPatientLoginId()+"','"+ patientsBean.getPatientWeight()+"','"+ patientsBean.getPatientAge()+"','"+ patientsBean.getPatientGender()+"','"+ patientsBean.getPatientMobile()+"')";
      try { 
        this.conn = this.dataSource.getConnection();
        this.cst = this.conn.prepareStatement(sql);
        this.cst.execute();
      } catch (Exception e) {
        e.printStackTrace();
      } finally {
        this.conn.close();
      } 
      return fetchPatients();
    }
    	//update 
	  public List<PatientsBean> updatePatient(PatientsBean patientsBean) throws SQLException {
      String sql = "update patient set patient_full_name='" + patientsBean.getPatientFullName()+ "',patient_email='" + patientsBean.getPatientEmail() +  
        "' ,patient_login_id ='"+ patientsBean.getPatientLoginId()+"', patient_weight ='"+ patientsBean.getPatientWeight()+"', patient_age ='"+ patientsBean.getPatientAge()+"',patient_gender ='"+ patientsBean.getPatientGender()+"' , patient_mobile ='"+ patientsBean.getPatientMobile()+"' where patient_id=" + patientsBean.getPatientId();
      System.out.println(sql);
      try {
        this.conn = this.dataSource.getConnection();
        this.cst = this.conn.prepareStatement(sql);
        this.cst.execute();
      } catch (Exception e) {
        e.printStackTrace();
      } finally {
        this.conn.close();
      } 
      return fetchPatients();
    } 
  //delete
  public List<PatientsBean> deletePatient(PatientsBean patientsBean) throws SQLException {
    String sql = "DELETE FROM patient WHERE patient_id=" + patientsBean.getPatientId();
    try {
      this.conn = this.dataSource.getConnection();
      this.cst = this.conn.prepareStatement(sql);
      this.cst.execute();
    } catch (Exception e) {
      e.printStackTrace(); 
    } finally {
      this.conn.close();
    }  
    return fetchPatients();
    
  }
}
