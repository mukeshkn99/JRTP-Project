package com.ashokit.service;

import com.ashokit.binding.Card;
import com.ashokit.binding.Loginform;
import com.ashokit.binding.Profile;


public interface CWService {

	public String cwlogin(Loginform login);
	public String forgotpwd(String email);
	public Card fetchDashboards();
	public Profile fetchProfileInfo(String email);
	public String editprofile(Profile edit);
}
