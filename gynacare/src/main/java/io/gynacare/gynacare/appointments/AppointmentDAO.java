package io.gynacare.gynacare.appointments;

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
public class AppointmentDAO {
    PreparedStatement cst = null;

    Connection conn = null;
    @Autowired
    DataSource dataSource;
//GET
public List<AppointmentBean> fetchAppointments() throws SQLException {
    String sqlQuery = "SELECT * FROM appointments";
    List<AppointmentBean> appointmentDetails = new ArrayList<>();
    try {
      this.conn = this.dataSource.getConnection();
      this.cst = this.conn.prepareStatement(sqlQuery);
      ResultSet rs = this.cst.executeQuery();
      while (rs.next()) {
        AppointmentBean fetch = new AppointmentBean();
        fetch.setAppId(rs.getBigDecimal("app_id"));
        fetch.setAppDate(rs.getDate("app_date"));
        fetch.setAppStatus(rs.getString("app_status"));
        fetch.setAppLocation(rs.getString("app_location"));
        fetch.setAppTime(rs.getString("app_time"));
        fetch.setAppGynaId(rs.getBigDecimal("app_gyna_id"));
        fetch.setAppPatientId(rs.getBigDecimal("app_patient_id"));
        fetch.setAppAppointmentDate(rs.getDate("app_appointment_date"));
        appointmentDetails.add(fetch);
      } 
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      this.conn.close();
    }  
    return appointmentDetails; 
    
  }
   //getting the next primary key 
public int getNextPrimaryKey() throws SQLException {
String column_name = "app_id";
String table_name = "appointments";
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
public List<AppointmentBean> createAppointment(AppointmentBean appointmentBean) throws SQLException {
    String sql = "INSERT INTO appointments(app_id, app_date, app_status,app_location,app_time,app_gyna_id, app_patient_id, app_appointment_date) VALUES ( "+ getNextPrimaryKey() +",'"+ appointmentBean.getAppDate()+"','"+ appointmentBean.getAppStatus()+"','"+ appointmentBean.getAppLocation()+"','"+ appointmentBean.getAppTime()+"','"+ appointmentBean.getAppGynaId()+"','"+ appointmentBean.getAppPatientId()+"','"+appointmentBean.getAppAppointmentDate()+"')";
    try { 
      this.conn = this.dataSource.getConnection();
      this.cst = this.conn.prepareStatement(sql);
      this.cst.execute();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      this.conn.close();
    } 
    return fetchAppointments();
  }
    //update 
        public List<AppointmentBean> updateAppointment(AppointmentBean appointmentBean) throws SQLException {
        String sql = "update appointments set app_date='" + appointmentBean.getAppDate()+ "', app_status ='" + appointmentBean.getAppStatus()+  
            "' ,app_location ='"+ appointmentBean.getAppLocation()+"', app_time ='"+ appointmentBean.getAppTime()+"', app_gyna_id ='"+ appointmentBean.getAppGynaId()+"', app_patient_id='"+ appointmentBean.getAppPatientId()+"', app_appointment_date = '"+appointmentBean.getAppAppointmentDate()+"' where app_id=" + appointmentBean.getAppId();
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
        return fetchAppointments();
        } 
    //delete
  public List<AppointmentBean> deleteAppointment(AppointmentBean appointmentBean) throws SQLException {
    String sql = "DELETE FROM appointments WHERE app_id=" + appointmentBean.getAppId();
    try {
      this.conn = this.dataSource.getConnection();
      this.cst = this.conn.prepareStatement(sql);
      this.cst.execute();
    } catch (Exception e) {
      e.printStackTrace(); 
    } finally {
      this.conn.close();
    }  
    return fetchAppointments();
    
  }



}
