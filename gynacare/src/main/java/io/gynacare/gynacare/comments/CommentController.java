package io.gynacare.gynacare.comments;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping({"/gynacare/comment"})
@CrossOrigin(origins = "http://localhost:4200")
public class CommentController {
    @Autowired
    CommentDAO commentDAO;
      //get 
	  @RequestMapping(method = {RequestMethod.GET}, value = {"/fetchComment"})
	  public List<CommentBean> fetchComment() throws SQLException {
	    return this.commentDAO.fetchComment();
	  }
	  //create 
	  @RequestMapping(method = {RequestMethod.POST}, value = {"/createComment"})
	  public List<CommentBean> createComment(@RequestBody CommentBean commentBean) throws SQLException {
	    return this.commentDAO.createComment(commentBean);
	  } 
		  //update 
	  @RequestMapping(method = {RequestMethod.POST}, value = {"/updateComment"})
	  public List<CommentBean> updateComment(@RequestBody CommentBean commentBean) throws SQLException {
	    return this.commentDAO.updateComment(commentBean);
	  }
	  
	  
	  //delete 
	  @RequestMapping(method = {RequestMethod.POST}, value = {"/deleteComment"})
	  public List<CommentBean> deleteComment(@RequestBody CommentBean commentBean) throws SQLException {
	    return this.commentDAO.deleteComment(commentBean);
	  }
}
 