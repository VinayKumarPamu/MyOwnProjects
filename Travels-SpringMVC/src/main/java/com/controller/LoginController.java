package com.controller;

import java.text.ParseException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.entity.User;
import com.entity.Journey;
import com.service.LoginService;

@Controller
public class LoginController {
	String Source;
	String Destination;
	String journeyDate;
	float price;
	int passNum;
	int id;
	int index;
	@Autowired
	LoginService loginService;

	@RequestMapping("/signup")
	public String redirectToSignUp(HttpServletRequest req, Model model) {
		return "signup";
	}
	@RequestMapping("/signin")
	public String redirectToLogin(HttpServletRequest req, Model model) {
		return "signin";
	}
	@RequestMapping("/validate-login")
	public String validateLogin(HttpServletRequest req, Model model) {
		System.out.println("Validater Invoked --");
		String userName = req.getParameter("userName");
		String passWord = req.getParameter("password");
		System.out.println(userName);
		System.out.println(passWord);
		User user = new User(userName, passWord);
		List<Object> UserCheck = loginService.signInUser(user);
		id=(int) UserCheck.get(1);//***************** Update Id's in all repo's
		System.out.println(id);
		if(UserCheck.get(0).equals(true)) {
			return "Travels-menu";
		}
		else {
			return "signin";
		}
	}//****** WORK in PROGRESS
	@RequestMapping("/travel-book")
	public String getTravelBook(){
		return "travel-book";

	}
	@RequestMapping("/create-user")
	public String createUser(HttpServletRequest incomingRequest, Model model) {
		String name=incomingRequest.getParameter("Name");
		String userName=incomingRequest.getParameter("UserName");
		String email = incomingRequest.getParameter("email");
		String password = incomingRequest.getParameter("password");
		String confirmPassword = incomingRequest.getParameter("confirm-password");
		User user=new User(0, name, userName, email, confirmPassword);
		loginService.signUpUser(user);
		System.out.println(
				String.format(" Details are email %s and password is %s and confirm password is %s",
						email, password, confirmPassword));
		return "Travels-menu";
	}
	@RequestMapping("/book-travel")
	public String bookJourney(HttpServletRequest incomingRequest, Model model) throws ParseException {
		Source=incomingRequest.getParameter("from_address");
		Destination=incomingRequest.getParameter("to_address");
		journeyDate = incomingRequest.getParameter("travel_date");
		passNum=Integer.parseInt(incomingRequest.getParameter("passinger_Number"));
		Journey jrny=new Journey(Source, Destination, journeyDate, passNum, id, price);
		Journey journeyDetails = loginService.journeyDetails(jrny);
		model.addAttribute("journey", journeyDetails);
		price=jrny.getPrice();
		return "Journey-Ticket";//Seating also pending
		//******** WORK in PROGRESS
	}
	@RequestMapping("/ConfirmTicket")
	public String confirmTicket(HttpServletRequest incomingRequest, Model model) {
		Journey jrny=new Journey(Source, Destination, journeyDate, passNum, id, price);
		loginService.confirmTicket(jrny);
		model.addAttribute("journey", jrny);
		return "success";
	}
	@RequestMapping("/history")
	public String getHistory(HttpServletRequest incomingRequest,Model model ) {
		Journey jrny=new  Journey();
		jrny.setId(id);
		List<Journey> retriveJourney = loginService.retriveJourney(jrny);
		model.addAttribute("history",retriveJourney);
		return "JourneyHistory";
	}
	@RequestMapping("/EditTicket")
	public String editjourneyDetails(HttpServletRequest incomingRequest,Model model ) {
		Journey jrny=new  Journey();
		jrny.setId(id);
		List<Journey> retriveJourney = loginService.retriveJourney(jrny);
		model.addAttribute("history",retriveJourney);
		return "EditTicket";
	}
	@RequestMapping("/paymentHistory")
	public String paymentHistory(HttpServletRequest incomingRequest,Model model ) {
		Journey jrny=new  Journey();
		jrny.setId(id);
		List<Journey> retriveJourney = loginService.retriveJourney(jrny);
		model.addAttribute("history",retriveJourney);
		return "TotalPayments";
	}
	@RequestMapping("/UpdateTickets")
	public String updateTickets(HttpServletRequest incomingRequest,Model model ) {
		index=Integer.parseInt(incomingRequest.getParameter("selectedIndex"));
		System.out.println(index);
		return "UpdatedTicket";
	}
	@RequestMapping("/UpdateTicket")
	public String updateTicket(HttpServletRequest incomingRequest,Model model ) {
		String Source=incomingRequest.getParameter("from_address");
		String Destination=incomingRequest.getParameter("to_address");
		int passNum=Integer.parseInt(incomingRequest.getParameter("passinger_Number"));
		Journey jrny=new Journey(Source, Destination, passNum);
		jrny.setId(id);
		System.out.println("id is "+id);
		List<Journey> retriveJourney = loginService.retriveJourney(jrny);
		System.out.println(retriveJourney.size());
		jrny.setJourneyDate(retriveJourney.get(index).getJourneyDate()); 
		Journey journeyDetails = loginService.journeyDetails(jrny);
		jrny.setPrice(journeyDetails.getPrice());
		System.out.println("update it");
		loginService.UpdateTicket(jrny);
		return "Travels-menu";
	}
}
