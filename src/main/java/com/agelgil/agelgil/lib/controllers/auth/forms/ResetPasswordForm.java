package com.agelgil.agelgil.lib.controllers.auth.forms;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ResetPasswordForm {
	

	@Size(min = 8, message = "Password should be atleast 8 characters long.")
	private String password;

	private String confirmPassword;

	private String token;

	@AssertTrue(message = "Passwords don't match.")
	public boolean isPasswordMatching(){
		if(password == null || confirmPassword == null)
			return true;
		return password.equals(confirmPassword);
	}

}
