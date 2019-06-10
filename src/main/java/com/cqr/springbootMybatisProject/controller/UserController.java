package com.cqr.springbootMybatisProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.thymeleaf.util.StringUtils;

import com.cqr.springbootMybatisProject.entity.User;
import com.cqr.springbootMybatisProject.service.UserService;

@Controller
@RequestMapping(value="user")
public class UserController {
	@Autowired
	private UserService userService;
   @RequestMapping(value = "welcome",method= RequestMethod.GET)
   public String welcome(){
	   return "index";
   }
   @RequestMapping(value = "login",method= RequestMethod.POST)
   public String login(User user,Model model){
	   User client = userService.checkLogin(user);
	   if(client != null){
		   model.addAttribute("msg", "succeed！");
		   return "succeed";
	   }
	   model.addAttribute("msg", "fail");
	   return "fail";
   }
   @RequestMapping(value = "register",method= RequestMethod.POST)
   public String register(User user,Model model){
	   boolean result = false;
	   User user1=null;
	   
	   if(user!=null&&!StringUtils.isEmpty(user.getUserName())&&!StringUtils.isEmpty(user.getPassword())){
		   user1= userService.selectByPrimaryKey(user.getUserName());
		   if(user1==null) {
		       result= userService.registerUser(user);
		   }
	   }
	   
	   if(result){
		   model.addAttribute("msg", "注册成功！");
		   return "index";
	   }
	   model.addAttribute("msg", "注册失败！");
	   return "index";
   }
}
