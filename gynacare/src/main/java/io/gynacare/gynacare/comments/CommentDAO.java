package io.gynacare.gynacare.comments;

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
public class CommentDAO {
    PreparedStatement cst = null;

    Connection conn = null;
    @Autowired
    DataSource dataSource;


    
    //GET
    public List<CommentBean> fetchComment() throws SQLException {
        String sqlQuery = "SELECT comment_id,comment_patient_id,patient_full_name,comment_datetime,comment_article_id,comment_message,comment_parent_comment_id FROM comments JOIN patient on patient.patient_id = comments.comment_patient_id;";
        List<CommentBean> comment= new ArrayList<>();
        try {
          this.conn = this.dataSource.getConnection();
          this.cst = this.conn.prepareStatement(sqlQuery);
          ResultSet rs = this.cst.executeQuery();
          while (rs.next()) {
            CommentBean fetch = new CommentBean();
            fetch.setCommentId(rs.getBigDecimal("comment_id"));
            fetch.setCommentPatientId(rs.getBigDecimal("comment_patient_id"));
            fetch.setPatientFullName(rs.getString("patient_full_name"));
            fetch.setCommentDatetime((rs.getString("comment_datetime")));
            fetch.setCommentArticleId(rs.getBigDecimal("comment_article_id"));
            fetch.setCommentMessage(rs.getString("comment_message"));
            fetch.setCommentParentCommentId(rs.getBigDecimal("comment_parent_comment_id"));
            comment.add(fetch);
          } 
        } catch (Exception e) {
          e.printStackTrace();
        } finally {
          this.conn.close();
        }  
        return comment; 
        
      }
           //getting the next primary key 
   public int getNextPrimaryKey() throws SQLException {
    String column_name = "comment_id";
    String table_name = "comments";
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
  public List<CommentBean> createComment(CommentBean commentBean) throws SQLException {
    String sql = "INSERT INTO comments(comment_id,comment_patient_id,comment_datetime,comment_article_id,comment_message,comment_parent_comment_id) VALUES ( "+ getNextPrimaryKey() +",'"+ commentBean.getCommentPatientId()+"','"+ commentBean.getCommentDatetime()+"','"+commentBean.getCommentArticleId()+"','"+ commentBean.getCommentMessage()+"','"+commentBean.getCommentParentCommentId()+"')";
    try { 
      this.conn = this.dataSource.getConnection();
      this.cst = this.conn.prepareStatement(sql);
      this.cst.execute();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      this.conn.close();
    } 
    return fetchComment();
  }
    //update 
        public List<CommentBean> updateComment(CommentBean commentBean) throws SQLException {
        String sql = "update comments set comment_patient_id='" + commentBean.getCommentPatientId()+ "',comment_datetime='" + commentBean.getCommentDatetime() +  
            "' ,comment_article_id ='"+ commentBean.getCommentArticleId()+"', comment_message ='"+commentBean.getCommentMessage() +"',comment_parent_comment_id = '"+commentBean.getCommentParentCommentId()+"' where comment_id=" + commentBean.getCommentId();
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
        return fetchComment();
        } 
           //delete
  public List<CommentBean> deleteComment(CommentBean commentBean) throws SQLException {
    String sql = "DELETE FROM comments WHERE comment_id=" + commentBean.getCommentId();
    try {
      this.conn = this.dataSource.getConnection();
      this.cst = this.conn.prepareStatement(sql);
      this.cst.execute();
    } catch (Exception e) {
      e.printStackTrace(); 
    } finally {
      this.conn.close();
    }  
    return fetchComment();
    
  }

}
