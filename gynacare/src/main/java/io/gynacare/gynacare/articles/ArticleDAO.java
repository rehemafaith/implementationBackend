package io.gynacare.gynacare.articles;

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
public class ArticleDAO {
    PreparedStatement cst = null;

    Connection conn = null;
    @Autowired
    DataSource dataSource;


    //GET
    public List<ArticleBean> fetchArticle() throws SQLException {
        String sqlQuery = "SELECT * FROM articles";
        List<ArticleBean> article = new ArrayList<>();
        try {
          this.conn = this.dataSource.getConnection();
          this.cst = this.conn.prepareStatement(sqlQuery);
          ResultSet rs = this.cst.executeQuery();
          while (rs.next()) {
            ArticleBean fetch = new ArticleBean();
            fetch.setArticleId(rs.getBigDecimal("article_id"));
            fetch.setArticleTitle(rs.getString("article_title"));
            fetch.setArticleBody((rs.getString("article_body")));
            fetch.setArticlePubDatetimeString(rs.getString("article_pub_datetime"));
            fetch.setArticleGynaId(rs.getBigDecimal("article_gyna_id"));
            article.add(fetch);
          } 
        } catch (Exception e) {
          e.printStackTrace();
        } finally {
          this.conn.close();
        }  
        return article; 
        
      }

        //getting the next primary key 
   public int getNextPrimaryKey() throws SQLException {
    String column_name = "article_id";
    String table_name = "articles";
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
  public List<ArticleBean> createArticle(ArticleBean articleBean) throws SQLException {
    String sql = "INSERT INTO articles(article_id,article_title,article_body,article_pub_datetime,article_gyna_id) VALUES ( "+ getNextPrimaryKey() +",'"+ articleBean.getArticleTitle()+"','"+ articleBean.getArticleBody()+"','"+articleBean.getArticlePubDatetimeString()+"','"+ articleBean.getArticleGynaId()+"')";
    try { 
      this.conn = this.dataSource.getConnection();
      this.cst = this.conn.prepareStatement(sql);
      this.cst.execute();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      this.conn.close();
    } 
    return fetchArticle();
  }
    //update 
        public List<ArticleBean> updateArticle(ArticleBean articleBean) throws SQLException {
        String sql = "update articles set article_title='" + articleBean.getArticleTitle()+ "',article_body='" + articleBean.getArticleBody() +  
            "' ,article_pub_datetime ='"+ articleBean.getArticlePubDatetimeString()+"', article_gyna_id ='"+ articleBean.getArticleGynaId()+"' where article_id=" + articleBean.getArticleId();
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
        return fetchArticle();
        } 
           //delete
  public List<ArticleBean> deleteArticle(ArticleBean articleBean) throws SQLException {
    String sql = "DELETE FROM articles WHERE article_id=" + articleBean.getArticleId();
    try {
      this.conn = this.dataSource.getConnection();
      this.cst = this.conn.prepareStatement(sql);
      this.cst.execute();
    } catch (Exception e) {
      e.printStackTrace(); 
    } finally {
      this.conn.close();
    }  
    return fetchArticle();
    
  }




}
