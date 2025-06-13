package com.diary;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RouteController {
		
	
	 @RequestMapping(value = { "/", "/new", "/edit/**", "/diary/**" })
	    public String forward() {
	        return "forward:/index.html";
	    }
}
