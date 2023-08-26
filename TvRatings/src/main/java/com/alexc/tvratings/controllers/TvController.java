package com.alexc.tvratings.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.alexc.tvratings.models.LoginUser;
import com.alexc.tvratings.models.Rating;
import com.alexc.tvratings.models.Show;
import com.alexc.tvratings.models.User;
import com.alexc.tvratings.services.RatingService;
import com.alexc.tvratings.services.ShowService;
import com.alexc.tvratings.services.UserService;

@Controller
public class TvController {
	@Autowired
	UserService userServ;
	@Autowired
	ShowService showServ;
	@Autowired
	RatingService rateServ;
	
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("newUser", new User());
		model.addAttribute("newLogin", new LoginUser());
		return "Login.jsp";
	}

	@PostMapping("/register")
	public String register(@Valid @ModelAttribute("newUser") User newUser, BindingResult result, Model model,
			HttpSession session) {

		User user = userServ.register(newUser, result);
		if (result.hasErrors()) {
			model.addAttribute("newLogin", new LoginUser());
			return "Login.jsp";
		}

		session.setAttribute("userId", newUser.getId());
		return "redirect:/shows";
	}

	@PostMapping("/login")
	public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin, BindingResult result, Model model,
			HttpSession session) {

		User user = userServ.login(newLogin, result);

		if (result.hasErrors()) {
			model.addAttribute("newUser", new User());
			return "Login.jsp";
		}

		session.setAttribute("userId", user.getId());
		return "redirect:/shows";
	}
	@GetMapping("/logout")
	public String logout(HttpSession session, Model model) {
		session.setAttribute("userId", null);
		return "redirect:/";
	}
	
	@GetMapping("/shows")
	public String dashboard(Model model, HttpSession session) {
		Long userId = (Long) session.getAttribute("userId");
		if(userId==null) {
			return "redirect:/logout";
		}
		User user = userServ.findUser(userId);
		List<Show> shows = showServ.getAll();
		model.addAttribute("user", user);
		model.addAttribute("shows", shows);
		return "Dashboard.jsp";
	}
	
	@GetMapping("/shows/new")
	public String newShow(@ModelAttribute("newShow") Show show, Model model, HttpSession session) {
		Long userId = (Long) session.getAttribute("userId");
		if(userId==null) {
			return "redirect:/logout";
		}
		return "NewShow.jsp";
	}
	@PostMapping("/shows/add")
	public String addShow(@Valid @ModelAttribute("newShow") Show show, BindingResult result, Model model, HttpSession session) {
		Long userId = (Long)session.getAttribute("userId");
		if(showServ.findShowByTitle(show.getTitle())!=null) {
			result.rejectValue("title", "Matches", "This title already exists.");
		}
		if(result.hasErrors()) {
			return "NewShow.jsp";
		}
		else {
			show.setShowCreator(userServ.findUser(userId));
			showServ.createShow(show, result);
			return "redirect:/shows";
		}
	}
	@GetMapping("/shows/delete/{id}")
	public String deleteListing(@PathVariable("id") Long id) {
		Show show = showServ.findShow(id);
		showServ.deleteShow(show);
		return "redirect:/shows";
	}
	@GetMapping("/shows/{id}")
	public String shows(@PathVariable("id") Long id, @ModelAttribute("newRating") Rating rating, Model model, HttpSession session) {
		Long userId = (Long) session.getAttribute("userId");
		if(userId==null) {
			return "redirect:/logout";
		}
		Show show = showServ.findShow(id);
		List<Rating> ratings = show.getRatings();
		model.addAttribute("ratings", ratings);
		model.addAttribute("show", show);
		return "Show.jsp";
	}
	
	@GetMapping("/shows/edit/{id}")
	public String editShow(@PathVariable("id") Long id, Model model, HttpSession session) {
		Long userId = (Long) session.getAttribute("userId");
		if(userId==null) {
			return "redirect:/logout";
		}
		Show show = showServ.findShow(id);
		model.addAttribute("show", show);
		return "EditShow.jsp";
	}

	@PutMapping("/shows/edit/{id}")
	public String updateShow(@PathVariable("id") Long id, @Valid @ModelAttribute("show") Show show, BindingResult result, Model model, HttpSession session) {
		if(showServ.findShowByTitle(show.getTitle())!=null) {
			result.rejectValue("title", "Matches", "This title already exists.");
		}
		if(result.hasErrors()) {
			return "EditShow.jsp";
		}
		else {
			Show showUpd = showServ.findShow(id);
			showUpd.setTitle(show.getTitle());
			showUpd.setDescription(show.getDescription());
			showUpd.setNetwork(show.getNetwork());
			showServ.updateShow(showUpd);
			return "redirect:/shows/" + id;
		}
	}
	
	@PostMapping("/shows/rating/{id}")
	public String addRating(@PathVariable("id") Long id, @Valid @ModelAttribute("newRating") Rating rating, BindingResult result, Model model, HttpSession session) {
		User user = userServ.findUser((Long) session.getAttribute("userId"));
		Show show = showServ.findShow(id);
		
		if(result.hasErrors()) {
			model.addAttribute("show", show);
			return "Show.jsp";
		}
		else {
			Rating newRating = new Rating();
			newRating.setShowRating(rating.getShowRating());
			newRating.setRatedShow(show);
			newRating.setReviewCreator(user);

			rateServ.createRating(newRating);
			return "redirect:/shows/" + id;
		}
	}
}
