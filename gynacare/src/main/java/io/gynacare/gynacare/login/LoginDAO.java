package io.gynacare.gynacare.login;

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
public class LoginDAO {
    PreparedStatement cst = null;

    Connection conn = null;
    @Autowired
    DataSource dataSource;

    //GET
    public List<LoginBean> fetchLoginDetails() throws SQLException {
      String sqlQuery = "SELECT * FROM login";
      List<LoginBean> loginDetails = new ArrayList<>();
      try {
        this.conn = this.dataSource.getConnection();
        this.cst = this.conn.prepareStatement(sqlQuery);
        ResultSet rs = this.cst.executeQuery();
        while (rs.next()) {
          LoginBean fetch = new LoginBean();
          fetch.setLoginId(rs.getBigDecimal("login_id"));
          fetch.setLoginUsername(rs.getString("login_user_name"));
          fetch.setLoginPassword(rs.getString("login_pass"));
          fetch.setLoginRank(rs.getString("login_rank"));
          loginDetails.add(fetch);
        } 
      } catch (Exception e) {
        e.printStackTrace();
      } finally {
        this.conn.close();
      }  
      return loginDetails; 
      
    }
    //create new Login Details
	  public List<LoginBean> createLoginDetails(LoginBean loginBean) throws SQLException {
      String sql = "INSERT INTO login(login_id, login_user_name, login_pass, login_rank) VALUES ( "+ getNextPrimaryKey() +",'"+ loginBean.getLoginUsername() +"','"+ loginBean.getLoginPassword()+"','"+ loginBean.getLoginRank()+"')";
      try { 
        this.conn = this.dataSource.getConnection();
        this.cst = this.conn.prepareStatement(sql);
        this.cst.execute();
      } catch (Exception e) {
        e.printStackTrace();
      } finally {
        this.conn.close();
      } 
      return fetchLoginDetails();
    }
  
  //getting the next primary key 
  public int getNextPrimaryKey() throws SQLException {
      String column_name = "login_id";
      String table_name = "login";
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
  
//update login information
  public List<LoginBean> updateLoginDetails(LoginBean loginBean) throws SQLException {
      String sql = "update login set login_user_name='" + loginBean.getLoginUsername()  + "',login_pass='" + loginBean.getLoginPassword()+ "', login_rank='" + loginBean.getLoginRank()+"' where login_id=" + loginBean.getLoginId();
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
      return fetchLoginDetails();
    } 
  
  //delete login details
  public List<LoginBean> deleteLoginDetails(LoginBean loginBean) throws SQLException {
    String sql = "DELETE FROM login WHERE login_id=" + loginBean.getLoginId();
    try {
      this.conn = this.dataSource.getConnection();
      this.cst = this.conn.prepareStatement(sql);
      this.cst.execute();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      this.conn.close();
    } 
    return fetchLoginDetails();
    
  }
}
