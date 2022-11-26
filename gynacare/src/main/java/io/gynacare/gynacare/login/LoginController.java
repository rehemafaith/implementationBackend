package io.gynacare.gynacare.login;

import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping({"/gynacare/login"})
@CrossOrigin(origins = "http://localhost:4200")
public class LoginController {
    @Autowired
    LoginDAO loginDAO;
   
  
    //get login details 
    @RequestMapping(method = {RequestMethod.GET}, value = {"/fetchLoginDetails"})
    public List<LoginBean> fetchLogin() throws SQLException {
      return this.loginDAO.fetchLoginDetails();
    }

   
  //create new login 	 
    @RequestMapping(method = {RequestMethod.POST}, value = {"/createLogin"})
    public List<LoginBean> createLoginDetails(@RequestBody LoginBean loginBean) throws SQLException {
      return this.loginDAO.createLoginDetails(loginBean);
    } 
    
  //update login
    @RequestMapping(method = {RequestMethod.POST}, value = {"/updateLogin"})
    public List<LoginBean> updateLoginDetails(@RequestBody LoginBean loginBean) throws SQLException {
      return this.loginDAO.updateLoginDetails(loginBean);
    }
    
    //delete department
    @RequestMapping(method = {RequestMethod.POST}, value = {"/deleteLoginDetails"})
    public List<LoginBean> deleteLoginDetails(@RequestBody LoginBean loginBean) throws SQLException {
      return this.loginDAO.deleteLoginDetails(loginBean);
    }
}
