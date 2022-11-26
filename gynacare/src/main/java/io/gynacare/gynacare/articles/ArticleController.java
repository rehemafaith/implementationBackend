package io.gynacare.gynacare.articles;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping({"/gynacare/article"})
@CrossOrigin(origins = "http://localhost:4200")
public class ArticleController {
    
    @Autowired
    ArticleDAO articleDAO;
      //get 
	  @RequestMapping(method = {RequestMethod.GET}, value = {"/fetchArticle"})
	  public List<ArticleBean> fetchArticle() throws SQLException {
	    return this.articleDAO.fetchArticle();
	  }
	  //create 
	  @RequestMapping(method = {RequestMethod.POST}, value = {"/createArticle"})
	  public List<ArticleBean> createArticle(@RequestBody ArticleBean articleBean) throws SQLException {
	    return this.articleDAO.createArticle(articleBean);
	  } 
		  //update 
	  @RequestMapping(method = {RequestMethod.POST}, value = {"/updateArticle"})
	  public List<ArticleBean> updateArticle(@RequestBody ArticleBean articleBean) throws SQLException {
	    return this.articleDAO.updateArticle(articleBean);
	  }
	  
	  
	  //delete 
	  @RequestMapping(method = {RequestMethod.POST}, value = {"/deleteArticle"})
	  public List<ArticleBean> deleteArtcle(@RequestBody ArticleBean articleBean) throws SQLException {
	    return this.articleDAO.deleteArticle(articleBean);
	  }
}
