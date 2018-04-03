package com.qa.accproj.controller;

import java.util.List;

import com.qa.accproj.dao.AppUserDAO;
import com.qa.accproj.formbean.AppUserForm;
import com.qa.accproj.model.AppUser;
import com.qa.accproj.validator.AppUserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
 
@Controller
public class MainController {
 
   @Autowired
   private AppUserDAO appUserDAO;
 
   @Autowired
   private AppUserValidator appUserValidator;
 
   @InitBinder
   protected void initBinder(WebDataBinder dataBinder) {
	   
      Object target = dataBinder.getTarget();
      if (target == null) return;
      
      if (target.getClass() == AppUserForm.class) {
         dataBinder.setValidator(appUserValidator);
      }
   }
 
   @RequestMapping("/")
   public String viewAccounts(Model model) {
 
      List<AppUser> list = appUserDAO.getAppUsers();
      model.addAttribute("accounts", list);
      return "accounts";
   }
 
   @RequestMapping("/registerSuccessful")
   public String viewRegisterSuccessful(Model model) {
 
      return "registerSuccessful";
   }
   
   @RequestMapping("/deleteSuccessful")
   public String viewUpdateSuccessful(Model model) {
 
      return "deleteSuccessful";
   }
   
   @RequestMapping("/editSuccessful")
   public String viewEditSuccessful(Model model) {
 
      return "editSuccessful";
   }
 
   @RequestMapping(value = "/register", method = RequestMethod.GET)
   public String viewRegister(Model model) {
	   AppUserForm form = new AppUserForm(0, null, null);
	   model.addAttribute("appUserForm", form);
      return "register";
   }
 
   @RequestMapping(value = "/register", method = RequestMethod.POST)
   public String updateRegister(Model model, //
         @ModelAttribute("appUserForm") @Validated AppUserForm appUserForm, //
         BindingResult result, //
         final RedirectAttributes redirectAttributes) {
	   if (result.hasErrors()) return "register";
	   
      AppUser newUser= null;
      try {
         newUser = appUserDAO.createAppUser(appUserForm);
      }
      catch (Exception e) {

         model.addAttribute("errorMessage", "Error: " + e.getMessage());
         return "register";
      }
 
      redirectAttributes.addFlashAttribute("flashUser", newUser);
       
      return "redirect:/registerSuccessful";
   }
   @RequestMapping(value = "/removeaccount", method = RequestMethod.GET)
   public String viewAccount(Model model1) {
	   AppUserForm form1 = new AppUserForm(0, null, null);
	   model1.addAttribute("appUserForm", form1);
      return "removeAccount";
   }
   
   @RequestMapping(value = "/removeaccount", method = RequestMethod.POST)
   public String updateAccount(Model model1, //
         @ModelAttribute("appUserForm") @Validated AppUserForm appUserForm, //
         BindingResult result, //
         final RedirectAttributes redirectAttributes) {

	   AppUser newUser= null;
      try {
         newUser = appUserDAO.deleteAppUser(appUserForm);
      }
      catch (Exception e) {

         model1.addAttribute("errorMessage", "Error: " + e.getMessage());
         return "removeAccount";
      }
 
      redirectAttributes.addFlashAttribute("flashUser", newUser);
       
      return "redirect:/deleteSuccessful";
   }
 
   @RequestMapping(value = "/editaccount", method = RequestMethod.GET)
   public String editAccount(Model model1) {
	   AppUserForm form2 = new AppUserForm(0, null, null);
	   model1.addAttribute("appUserForm", form2);
      return "editaccount";
   }

   @RequestMapping(value = "/editaccount", method = RequestMethod.POST)
   public String editAccount(Model model2, //
         @ModelAttribute("appUserForm") @Validated AppUserForm appUserForm, //
         BindingResult result, //
         final RedirectAttributes redirectAttributes) {

	   if (result.hasErrors()) return "editaccount";
	   AppUser newUser= null;
      try {
         newUser = appUserDAO.editAppUser(appUserForm);
      }
      catch (Exception e) {

         model2.addAttribute("errorMessage", "Error: " + e.getMessage());
         return "editaccount";
      }
 
      redirectAttributes.addFlashAttribute("flashUser", newUser);
       
      return "redirect:/editSuccessful";
   }
}