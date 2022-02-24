package com.agelgil.agelgil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.agelgil.agelgil.admin.data.models.Admin;
import com.agelgil.agelgil.admin.data.repositories.AdminRepository;
import com.agelgil.agelgil.hotel.data.models.City;
import com.agelgil.agelgil.hotel.data.models.Service.ServiceType;
import com.agelgil.agelgil.hotel.data.repositories.CityRepository;
import com.agelgil.agelgil.hotel.data.repositories.ServiceTypeRepository;
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
	public CommandLineRunner initialSetupRunner(
		AdminRepository adminRepository,
		UserManager userManager,
		ServiceTypeRepository serviceTypeRepository,
		CityRepository cityRepository
	){

		return new CommandLineRunner(){

			private void createAdminAccount(){
				String ADMIN_USERNAME = "admin@agelgil.com";
				String ADMIN_PASSWORD = "adminpassword";
				String ADMIN_FIRST_NAME = "John";
				String ADMIN_LAST_NAME = "Doe";


				if(userManager.userExists(ADMIN_USERNAME)){
					log.info("Admin Already Creating. Skipping...");
					return;
				}
				User adminUser = userManager.createUser(ADMIN_USERNAME, ADMIN_PASSWORD, Role.ADMIN, true);
				adminRepository.save(
					new Admin(adminUser, ADMIN_FIRST_NAME, ADMIN_LAST_NAME)
				);
				log.info(
					String.format("Admin created with credentials: username=%s, password=%s", ADMIN_USERNAME, ADMIN_PASSWORD)
				);

			}

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

			private List<City> getCities(){

				List<String> cityNames = Arrays.asList(
					"Addis Ababa",
					"Mekelle",
					"Gondar",
					"Adama",
					"Hawassa",
					"Bahir Dar",
					"Dire Dawa",
					"Sodo",
					"Dessie",
					"Jimma",
					"Jijiga",
					"Shashamane",
					"Bishoftu",
					"Arba Minch",
					"Hosaena",
					"Harar",
					"Dilla",
					"Nekemte",
					"Debre Birhan",
					"Asella",
					"Debre Mark'os",
					"Kombolcha",
					"Debre Tabor",
					"Adigrat",
					"Weldiya",
					"Areka",
					"Sebeta",
					"Burayu",
					"Shire (Inda Selassie)",
					"Ambo",
					"Arsi Negele",
					"Aksum",
					"Gambela",
					"Bale Robe",
					"Butajira",
					"Batu",
					"Boditi",
					"Adwa",
					"Yirgalem",
					"Waliso",
					"Welkite",
					"Gode",
					"Meki",
					"Negele Borana",
					"Alaba Kulito",
					"Alamata",
					"Chiro",
					"Tepi",
					"Durame",
					"Goba",
					"Assosa",
					"Gimbi",
					"Wukro",
					"Haramaya",
					"Mizan Teferi",
					"Sawla",
					"Mojo",
					"Dembi Dolo",
					"Aleta Wendo",
					"Metu",
					"Mota",
					"Fiche",
					"Finote Selam",
					"Bule Hora Town",
					"Bonga",
					"Kobo",
					"Jinka",
					"Dangila",
					"Degehabur",
					"Dimtu",
					"Agaro"
				);


				List<City> cities = new ArrayList<>();

				cityNames.forEach(
					cityName -> {
						cities.add(new City(cityName, ""));
					}
				);

				return cities;

			}

			private void insertCities(){
				
				log.info(
					String.format("Inserting Cities...")
				);

				List<City> cities = getCities();

				if(cities.size() <= cityRepository.count())
					return;

				cities.forEach(
					city -> {
						cityRepository.save(city);
					}
				);



			}

			@Override
			public void run(String... args) throws Exception {
				createAdminAccount();
				createServiceTypes();
				insertCities();
			}
			
		};

	}

}
