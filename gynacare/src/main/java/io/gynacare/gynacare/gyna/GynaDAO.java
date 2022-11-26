package io.gynacare.gynacare.gyna;

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
public class GynaDAO {
    
    PreparedStatement cst = null;

    Connection conn = null;
    @Autowired
    DataSource dataSource;


    //GET
    public List<GynaBean> fetchGynaDetails() throws SQLException {
        String sqlQuery = "SELECT * FROM gyna";
        List<GynaBean> gynaDetails = new ArrayList<>();
        try {
          this.conn = this.dataSource.getConnection();
          this.cst = this.conn.prepareStatement(sqlQuery);
          ResultSet rs = this.cst.executeQuery();
          while (rs.next()) {
            GynaBean fetch = new GynaBean();
            fetch.setGynaId(rs.getBigDecimal("gyna_id"));
            fetch.setGynaFullName(rs.getString("gyna_full_name"));
            fetch.setGynaRegNo((rs.getBigDecimal("gyna_reg_no")));
            fetch.setGynaLocation(rs.getString("gyna_location"));
            fetch.setGynaEmail(rs.getString("gyna_email"));
            fetch.setGynaMobile(rs.getString("gyna_mobile"));
            fetch.setGynaLoginId(rs.getBigDecimal("gyna_login_id"));
            gynaDetails.add(fetch);
          } 
        } catch (Exception e) {
          e.printStackTrace();
        } finally {
          this.conn.close();
        }  
        return gynaDetails; 
        
      }
       //getting the next primary key 
   public int getNextPrimaryKey() throws SQLException {
    String column_name = "gyna_id";
    String table_name = "gyna";
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
  public List<GynaBean> createGyna(GynaBean gynaBean) throws SQLException {
    String sql = "INSERT INTO gyna(gyna_id, gyna_full_name,gyna_reg_no,gyna_location,gyna_email,gyna_mobile, gyna_login_id) VALUES ( "+ getNextPrimaryKey() +",'"+ gynaBean.getGynaFullName()+"','"+ gynaBean.getGynaRegNo()+"','"+ gynaBean.getGynaLocation()+"','"+ gynaBean.getGynaEmail()+"','"+ gynaBean.getGynaMobile()+"','"+ gynaBean.getGynaLoginId()+"')";
    try { 
      this.conn = this.dataSource.getConnection();
      this.cst = this.conn.prepareStatement(sql);
      this.cst.execute();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      this.conn.close();
    } 
    return fetchGynaDetails();
  }
    //update 
        public List<GynaBean> updateGyna(GynaBean gynaBean) throws SQLException {
        String sql = "update gyna set gyna_full_name='" + gynaBean.getGynaFullName()+ "',gyna_reg_no='" + gynaBean.getGynaRegNo() +  
            "' ,gyna_location ='"+ gynaBean.getGynaLocation()+"', gyna_email ='"+ gynaBean.getGynaEmail()+"', gyna_mobile ='"+ gynaBean.getGynaMobile()+"',gyna_login_id ='"+ gynaBean.getGynaLoginId()+"' where gyna_id=" + gynaBean.getGynaId();
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
        return fetchGynaDetails();
        } 
           //delete
  public List<GynaBean> deleteGyna(GynaBean gynaBean) throws SQLException {
    String sql = "DELETE FROM gyna WHERE gyna_id=" + gynaBean.getGynaId();
    try {
      this.conn = this.dataSource.getConnection();
      this.cst = this.conn.prepareStatement(sql);
      this.cst.execute();
    } catch (Exception e) {
      e.printStackTrace(); 
    } finally {
      this.conn.close();
    }  
    return fetchGynaDetails();
    
  }
}
