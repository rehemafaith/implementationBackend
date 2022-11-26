package io.gynacare.gynacare.patients;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping({"/gynacare/patients"})
@CrossOrigin(origins = "http://localhost:4200")
public class PatientsController {
    @Autowired
    PatientsDAO patientsDAO;
      //get 
	  @RequestMapping(method = {RequestMethod.GET}, value = {"/fetchpatients"})
	  public List<PatientsBean> fetchPatients() throws SQLException {
	    return this.patientsDAO.fetchPatients();
	  }
	  //create 
	  @RequestMapping(method = {RequestMethod.POST}, value = {"/createpatient"})
	  public List<PatientsBean> createPatient(@RequestBody PatientsBean patientBean) throws SQLException {
	    return this.patientsDAO.createPatient(patientBean);
	  } 
		  //update 
	  @RequestMapping(method = {RequestMethod.POST}, value = {"/updatepatient"})
	  public List<PatientsBean> updatePatient(@RequestBody PatientsBean patientBean) throws SQLException {
	    return this.patientsDAO.updatePatient(patientBean);
	  }
	  
	  
	  //delete 
	  @RequestMapping(method = {RequestMethod.POST}, value = {"/deletePatient"})
	  public List<PatientsBean> deletePatient(@RequestBody PatientsBean patientsBean) throws SQLException {
	    return this.patientsDAO.deletePatient(patientsBean);
	  }
	  
}
