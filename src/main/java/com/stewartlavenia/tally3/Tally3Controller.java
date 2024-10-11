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

// ************************ SEARCH FOR A USER ************************
	
	// displays the search form
	@GetMapping("/search")
	public String showSearchForm() {
		return "searchFormTwo";
	}
	// 7-31 M&V IS REPLACED WITH Model, because I need to loop thru the DB
	
	@GetMapping("/find")	
	public String showSearchResults(@RequestParam(value="firstName", required=false)String firstName,
			@RequestParam(value="lastName", required=false) String lastName, Model m) {
		
		
		List<Users> listResults = dao.searchForUser(firstName, lastName);
		
		m.addAttribute("firstName", firstName);
		m.addAttribute("lastName", lastName);
		m.addAttribute("searchUsers", listResults);
		
		try {
			
			if(listResults == null) {
				//return "fragments :: nameNotFound";
			return "searchFormTwo";
		//					return "searchFormTwo/fragments :: nameNotFound";
			} else {
			
			System.out.println("listResults should be here from Controller");
			System.out.println(firstName);
			System.out.println(listResults.size());
			return "searchFormTwo";
			//			return "fragments :: separator";
			
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/";
		}
	
	}
	
	
	
	
	
	/*
	// 7-26 M&V | Model, but not both because they accomplish the same task
	@GetMapping("/find")	
	public List<Users> showSearchResults(@RequestParam(required = false)String firstName,
			@RequestParam(required = false) String lastName, ModelAndView mav) {
		
		mav = new ModelAndView("fragments");
		List<Users> resultList= dao.searchForUser(firstName, lastName);
		
		mav.addObject("firstName", firstName);
		mav.addObject("lastName", lastName);
		//.setViewName("fragments");
		
		System.out.println("listResults should be here");
		System.out.println(firstName);
		System.out.println(resultList);
		return resultList;
//		return mav;
		
	}
	
	
	@RequestMapping("/search")
	public ModelAndView showSearchForm(@PathVariable("first_name") String first_name, String last_name) {

		ModelAndView mav = new ModelAndView("searchFormTwo");
		List<Users> user = dao.acquireFNorLN(first_name, last_name);
		mav.addObject("Users", user);
		return mav;
	}
	*/
	/*
	// search for a user
	
	//@GetMapping("/find")
		public String findUser(String firstName, String lastName, Model theModel) {
//			Users listResults = dao.searchForUser(null, null, null, null);
		System.out.println(firstName);
		List <Users> listResults = dao.acquireFNorLN(firstName, lastName);
			theModel.addAttribute("searchUsers", listResults);
			System.out.println("listResults should be here");
			System.out.println(firstName);
			System.out.println(listResults); //[] empty set
			return "fragments";
		
		}
/*
		
		
	/** 
	@RequestMapping("/search")
	public ModelAndView showSearchForm(@PathVariable("first_name, last_name, email, phone") String first_name, String last_name, String email, String phone) {

		ModelAndView mav = new ModelAndView("");
//		Users user = dao.get(user_id);
		Users user = dao.searchForUser(first_name, last_name, email, phone);
		mav.addObject("searchUsers", user);
		return mav;
	}
		
// ************************ TUTORIAL ************************
	// sp Web M&V |08| tutorial
	// first method is the model
	// can't have dup@RequestMapping(value="album", method=RequestMethod.GET)
	public String userAlbum(int user_id, String firstName, String lastName, Model m) {
		m.addAttribute("user_id", 101);
		m.addAttribute(firstName, "Sammy");
		m.addAttribute(lastName, "Davis");
		
		return "fragments";
	}
	
	
	// sp Web M&V |08| tutorial
	// second method is M&V
	@RequestMapping(value="/album", method=RequestMethod.GET)
	public ModelAndView showUserAlbum(String firstName, String lastName) {

		ModelAndView mav = new ModelAndView("fragments");
		mav.addObject("user_id", 101);
		mav.addObject("firstName", "Sammy");
		mav.addObject("lastName", "Davis");
		return mav;
	}
	
	// sp Web M&V |08| TUTORIAL ENDS ************************ 
**/
}

