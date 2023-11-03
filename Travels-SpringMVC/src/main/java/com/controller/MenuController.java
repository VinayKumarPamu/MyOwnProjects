package com.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MenuController {
	@RequestMapping("/Travels-menu")
	public String getmenu(HttpServletRequest incomingRequest,Model model ) {
		return "Travels-menu";
	}
}
