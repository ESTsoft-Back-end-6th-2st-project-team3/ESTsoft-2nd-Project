package com.estsoft.estsoft2ndproject.controller.api;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.estsoft.estsoft2ndproject.domain.dto.user.RegisterRequest;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
	@GetMapping("/member/login")
	public String login() {
		return "login";
	}

	@GetMapping("/")
	public String index() {
		return "index";
	}

	@GetMapping("/userData")
	@ResponseBody
	public OAuth2User userData(@AuthenticationPrincipal OAuth2User oAuth2User) {
		return oAuth2User;
	}

	@GetMapping("/member/register")
	public String register(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();

		String email = (String)session.getAttribute("email");
		String nickname = (String)session.getAttribute("nickname");
		String profileImageUrl = (String)session.getAttribute("profileImageUrl");

		model.addAttribute("email", email);
		model.addAttribute("nickname", nickname);
		model.addAttribute("profileImageUrl", profileImageUrl);

		return "register";
	}

	@PostMapping("/member/register")
	public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest, HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute("isComplete", "true");
		session.setAttribute("nickname", registerRequest.getNickname());
		session.setAttribute("profileImageUrl", registerRequest.getProfileImageUrl());
		session.setAttribute("selfIntro", registerRequest.getSelfIntro());
		session.setAttribute("snsLink", registerRequest.getSnsLink());

		return ResponseEntity.ok("success");
	}
}
