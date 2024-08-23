package com.shockweb.db;

import com.shockweb.db.domain.dto.AddressDto;
import com.shockweb.db.domain.dto.UserDto;
import com.shockweb.db.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class DbApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DbApplication.class, args);
	}

	@Autowired
	private UserService userService;

	@Override
	public void run(String... args) throws Exception {
		//==============================================================================================================
//		// create a userdto object
//		UserDto newUser = new UserDto();
//		newUser.setFirstName("John");
//		newUser.setLastName("Doe");
//		newUser.setEmail("johndoe@gmail.com");
//		newUser.setPhone(1234567890);
//		newUser.setPassword("badpass");
//
//		AddressDto defaultAddress = new AddressDto(5, "k1", "321 Pine St", "", "Phoenix", "AZ", 85001, "USA");
//		newUser.setDefaultAddress(defaultAddress);
//
//		System.out.println("New user: " + newUser);
//		// save the user
//		userService.saveNewUser(newUser);
		//==============================================================================================================

		//==============================================================================================================
		// get user by email
//		UserDto user = userService.getUserByEmail("johnde@gmail.com");
//		System.out.println(user);
		//==============================================================================================================

		//==============================================================================================================
		// create a new address
//		AddressDto newAddress = new AddressDto(6, "k2", "901 Maple St", "", "Philadelphia", "PA", 19102, "USA");
//		userService.saveNewAddress("johndoe@gmail.com", newAddress);
		//==============================================================================================================

		//==============================================================================================================
//		// get default address by email
//		AddressDto defaultAddress = userService.getDefaultAddressByEmail("johndoe@gmail.com");
//		System.out.println(defaultAddress);
		//==============================================================================================================

		// ==============================================================================================================
		// get all addresses by email
		List<AddressDto> addresses = userService.getAllAddressesByEmail("johndoe@gmail.com");
		System.out.println(addresses);

		//==============================================================================================================
	}

}
