package com.agelgil.agelgil;

<<<<<<< HEAD
import com.agelgil.agelgil.admin.data.models.Admin;
import com.agelgil.agelgil.admin.data.repositories.AdminRepository;
=======
import java.util.Arrays;

import com.agelgil.agelgil.admin.data.models.Admin;
import com.agelgil.agelgil.admin.data.repositories.AdminRepository;
import com.agelgil.agelgil.hotel.data.models.Service.ServiceType;
import com.agelgil.agelgil.hotel.data.repositories.ServiceTypeRepository;
>>>>>>> 8953eb0f8c1aeabb43e045f293faf7b38a4da5a5
import com.agelgil.agelgil.lib.data.models.auth.User;
import com.agelgil.agelgil.lib.data.models.auth.User.Role;
import com.agelgil.agelgil.lib.extra.auth.UserManager;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import lombok.extern.slf4j.Slf4j;


@SpringBootApplication
@Slf4j
public class AgelgilApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgelgilApplication.class, args);
	}


	@Bean
	public CommandLineRunner initialSetup(
		AdminRepository adminRepository,
<<<<<<< HEAD
		UserManager userManager
=======
		UserManager userManager,
		ServiceTypeRepository serviceTypeRepository
>>>>>>> 8953eb0f8c1aeabb43e045f293faf7b38a4da5a5
	){

		return new CommandLineRunner(){

			private void createAdminAccount(){
				String ADMIN_USERNAME = "admin@agelgil.com";
				String ADMIN_PASSWORD = "adminpassword";
				String ADMIN_FIRST_NAME = "Betsegaw";
				String ADMIN_LAST_NAME = "Lemma";


				if(userManager.userExists(ADMIN_USERNAME)){
					log.info("Admin Already Creating. Skipping...");
					return;
				}
				User adminUser = userManager.createUser(ADMIN_USERNAME, ADMIN_PASSWORD, Role.ADMIN);
				adminRepository.save(
					new Admin(adminUser, ADMIN_FIRST_NAME, ADMIN_LAST_NAME)
				);
				log.info(
					String.format("Admin created with credentials: username=%s, password=%s", ADMIN_USERNAME, ADMIN_PASSWORD)
				);

			}

<<<<<<< HEAD
			@Override
			public void run(String... args) throws Exception {
				createAdminAccount();
=======
			private void createServiceTypes(){
				if(serviceTypeRepository.findAll().iterator().hasNext())
					return;
				
				serviceTypeRepository.saveAll(
					Arrays.asList(
						new ServiceType("Rooms", ServiceType.Unit.NIGHT),
						new ServiceType("Spa", ServiceType.Unit.DAY),
						new ServiceType("Gym", ServiceType.Unit.MONTH)
					)
				);
			}

			@Override
			public void run(String... args) throws Exception {
				createAdminAccount();
				createServiceTypes();
>>>>>>> 8953eb0f8c1aeabb43e045f293faf7b38a4da5a5
			}
			
		};

	}

}
