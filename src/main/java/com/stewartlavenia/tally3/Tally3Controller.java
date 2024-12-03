package com.stewartlavenia.tally3;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.stewartlavenia.tally3.entity.Users;

//@RestController
@Controller
public class Tally3Controller {
	
	@Autowired 
	private Tally3Dao dao;
	
	// view resolve error
	@GetMapping("/home")
	public String viewHomePg(Model theModel) {
		List<Users> listUsers = dao.list();
		theModel.addAttribute("listUsers", listUsers);
//		System.out.println(listUsers);
		return "home";
	
	}

	// view customer list
	@GetMapping("/customers")
	public String viewCustomers(Model theModel) {
		List<Users> listCustomers = dao.customerList();
		theModel.addAttribute("listCustomers", listCustomers);
//		System.out.println(listCustomers);
		return "customers";
	
	}
	
	@RequestMapping("/new")
	public String showUserForm(Model m) {
		Users users = new Users();
		m.addAttribute("users", users);
		return "newUserForm";
	}
	
	@RequestMapping(value= "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("users") Users user) {
		dao.save(user);
		return "redirect:/home";
	}
	
	@RequestMapping("/edit/{user_id}")
	public ModelAndView showEditForm(@PathVariable("user_id") int user_id) {

		ModelAndView mav = new ModelAndView("editForm");
		Users user = dao.get(user_id);
		mav.addObject("Users", user);
		return mav;
	}
	
	@RequestMapping(value ="/update", method=RequestMethod.POST)
	public String update(@ModelAttribute("users") Users user) { 
		dao.update(user);
		
		return "redirect:/home";
	}
	
	@RequestMapping(value="/delete{user_id}", method=RequestMethod.GET)
	public String delete(@PathVariable("user_id") int user_id) {
//		jdbcTemplate.delete("user_id");
		dao.delete(user_id);
		return "redirect:/home";
	}

// ************************ SEARCH FOR USER First & Last name ************************
	
	// displays the search form
	@GetMapping("/search")
	public String showSearchForm() {
		return "searchFormTwo";
	}
	
	@GetMapping("/find")	
	public String showSearchResults(@RequestParam(value="firstName", required=false)String firstName,
			@RequestParam(value="lastName", required=false) String lastName, Model m) {
		
		
		List<Users> listResults = dao.searchForUser(firstName, lastName);
		
		m.addAttribute("firstName", firstName);
		m.addAttribute("lastName", lastName);
		m.addAttribute("searchUsers", listResults);
		

		try {
			
			if(listResults == null) {
				
				String msg = "Sorry " + firstName + " nor " +lastName + " was found in DB";
				m.addAttribute("message", msg);
				
				System.out.println(msg);
				return "searchFormTwo";
				
			} else {
			
			System.out.println("listResults are from the Controller" + listResults);
			System.out.println("First name is from controller: " + firstName);
			System.out.println(listResults.size());
			return "searchFormTwo";
			
			}
	
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/";
		}

	} // showSearchResults end
	
	// ************************ SEARCH FOR USER By First Name ************************
	// displays the search form
		@GetMapping("/searchByName")
		public String showFirstAndLastNameSearchForm() {
			return "searchFormByName";
		}
		
		@GetMapping("/findByName")	
		public String showFirstNameSearchResults(@RequestParam(value="firstName", required=false)String firstName,
			Model m) {
			List<Users> firstNameListResults = dao.searchByFirstName(firstName);
			
			m.addAttribute("firstName", firstName);
			//m.addAttribute("searchUsersByName", firstNameListResults);
			m.addAttribute("searchUsers", firstNameListResults);
			try {
				
				if(firstNameListResults == null) {
					
					String msg = "Sorry " + firstName + " wasn't found in the DataBase!";
					m.addAttribute("message", msg);
					
					System.out.println(msg);
					return "searchFormByName";
					
				} else {
					// this code only runs when there are results!!!
					System.out.println("first name list Results are from the Controller" + firstNameListResults);
					System.out.println(firstName);
					System.out.println(firstNameListResults.size());
					return "searchFormByName";
					
					}
			
				} catch (Exception e) {
					e.printStackTrace();
					return "redirect:/";
				}

			} // showFirstNameSearchResults end
			
	// ************************ SEARCH FOR USER Last name ************************
			
		@GetMapping("/findByLastName")	
		public String showLastNameSearchResults(@RequestParam(value="lastName", required=false)String lastName,
			Model m) {
			List<Users> lastNameListResults = dao.searchByLastName(lastName);
			
			m.addAttribute("lastName", lastName);
			m.addAttribute("searchUsers", lastNameListResults);
			try {
				
				if(lastNameListResults == null) {
					
					String msg = "Sorry " + lastName + " wasn't found in the DataBase!";
					m.addAttribute("message", msg);
					
					System.out.println(msg);
					return "searchFormByName";
					
				} else {
					// this code only runs when there are results!!!
					System.out.println("Last name list Results are from the Controller" + lastNameListResults);
					System.out.println(lastName);
					System.out.println(lastNameListResults.size());
					return "searchFormByName";
					
					}
			
				} catch (Exception e) {
					e.printStackTrace();
					return "redirect:/";
				}

			} // showFirstNameSearchResults end
		
		
		
} // Tally3Controller end

