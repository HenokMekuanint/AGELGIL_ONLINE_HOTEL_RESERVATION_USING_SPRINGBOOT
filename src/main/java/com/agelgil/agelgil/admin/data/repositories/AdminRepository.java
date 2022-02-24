package com.agelgil.agelgil.admin.data.repositories;

import java.util.List;

import com.agelgil.agelgil.admin.data.models.Admin;
import com.agelgil.agelgil.lib.data.models.auth.User;

import org.springframework.data.repository.CrudRepository;


public interface AdminRepository extends CrudRepository<Admin, String>{

	public Admin findByUser(User user);

}
