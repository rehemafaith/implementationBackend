package io.gynacare.gynacare.appointments;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping({"/gynacare/appointments"})
@CrossOrigin(origins = "http://localhost:4200")
public class AppointmentController {
    @Autowired
    AppointmentDAO appointmentDAO;
      //get 
	  @RequestMapping(method = {RequestMethod.GET}, value = {"/fetchAppointment"})
	  public List<AppointmentBean> fetchAppointment() throws SQLException {
	    return this.appointmentDAO.fetchAppointments();
	  }
	  //create 
	  @RequestMapping(method = {RequestMethod.POST}, value = {"/createAppointment"})
	  public List<AppointmentBean> createAppointment(@RequestBody AppointmentBean appointmentBean) throws SQLException {
	    return this.appointmentDAO.createAppointment(appointmentBean);
	  } 
		  //update 
	  @RequestMapping(method = {RequestMethod.POST}, value = {"/updateAppointment"})
	  public List<AppointmentBean> updateAppointment(@RequestBody AppointmentBean appointmentBean) throws SQLException {
	    return this.appointmentDAO.updateAppointment(appointmentBean);
	  }
	  
	  
	  //delete 
	  @RequestMapping(method = {RequestMethod.POST}, value = {"/deleteAppointment"})
	  public List<AppointmentBean> deleteAppointment(@RequestBody AppointmentBean appointmentBean) throws SQLException {
	    return this.appointmentDAO.deleteAppointment(appointmentBean);
	  }
}
