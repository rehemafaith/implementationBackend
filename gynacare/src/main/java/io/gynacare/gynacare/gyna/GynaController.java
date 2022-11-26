package io.gynacare.gynacare.gyna;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping({"/gynacare/gyna"})
@CrossOrigin(origins = "http://localhost:4200")
public class GynaController {
    
    @Autowired
    GynaDAO gynaDAO;
      //get 
	  @RequestMapping(method = {RequestMethod.GET}, value = {"/fetchgyna"})
	  public List<GynaBean> fetchGyna() throws SQLException {
	    return this.gynaDAO.fetchGynaDetails();
	  }
	  //create 
	  @RequestMapping(method = {RequestMethod.POST}, value = {"/creategyna"})
	  public List<GynaBean> createGyna(@RequestBody GynaBean gynaBean) throws SQLException {
	    return this.gynaDAO.createGyna(gynaBean);
	  } 
		  //update 
	  @RequestMapping(method = {RequestMethod.POST}, value = {"/updategyna"})
	  public List<GynaBean> updateGyna(@RequestBody GynaBean gynaBean) throws SQLException {
	    return this.gynaDAO.updateGyna(gynaBean);
	  }
	  
	  
	  //delete 
	  @RequestMapping(method = {RequestMethod.POST}, value = {"/deleteGyna"})
	  public List<GynaBean> deleteGyna(@RequestBody GynaBean gynaBean) throws SQLException {
	    return this.gynaDAO.deleteGyna(gynaBean);
	  }
}
