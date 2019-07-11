/*package com.bizleap.hr.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AbstractController {

	@Autowired
	private HttpSession httpSession;
	
	public void clearSession() {
		httpSession.invalidate();
	}

	@ModelAttribute("user")
	public User initUser() {
		UserProfile profile = (UserProfile) httpSession.getAttribute("profile");
		if (profile == null)
			return null;
		return profile.getUser();
	}

	public UserProfile getUserProfile() {
		return (UserProfile) httpSession.getAttribute("profile");
	}

	public LoginProfile getLoginProfile() {
		return this.getUserProfile().getLoginProfile();
	}

	protected boolean isContain(List<User> userList, Role role) {
		for (User user : userList) {
			if (user.getRole().isSameBoId(role))
				return true;
		}
		return false;
	}
}
*/