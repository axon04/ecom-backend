package com.shockweb.db;

import com.shockweb.db.domain.dto.user.AddressDto;
import com.shockweb.db.domain.dto.user.UserDto;
import com.shockweb.db.service.UserService;
import com.shockweb.db.testdata.TestData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class DbApplication implements CommandLineRunner {

	private final UserService userService;

	public DbApplication(UserService userService) {
		this.userService = userService;
	}

	public static void main(String[] args) {
		SpringApplication.run(DbApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		//==============================================================================================================
		UserDto user1 = TestData.getUser1();
		UserDto user2 = TestData.getUser2();

		AddressDto address1 = TestData.addressDtoList().get(0);
		AddressDto address2 = TestData.addressDtoList().get(1);
		AddressDto address3 = TestData.addressDtoList().get(2);
		AddressDto address4 = TestData.addressDtoList().get(3);
		AddressDto address5 = TestData.addressDtoList().get(4);
		AddressDto address6 = TestData.addressDtoList().get(5);

		//==============================================================================================================
//		// save new user
//		userService.saveNewUser(user1, address1);
//		userService.saveNewUser(user2, address2);

		//==============================================================================================================

		//==============================================================================================================
//		// get user by email
//		UserDto user = userService.getUserByEmail("diana.rider@beeg.com");
//		log.info("User: {}", user);
		//==============================================================================================================

		//==============================================================================================================
		// create a new address
//		userService.saveNewAddress("diana.rider@beeg.com", address3);
		//==============================================================================================================

		// ==============================================================================================================
		// get all addresses by email
//		List<AddressDto> addresses = userService.getAllAddressesByEmail("dani.daniels@beeg.com");
//		addresses.forEach(addressDto -> log.info("Address: {}", addressDto));
		//==============================================================================================================

		//==============================================================================================================
		// update user (full and partial -- leave the fields that you don't want to update as null)
		// create a sample dto for updating a user
//		UserDto updatedUser = new UserDto();
//		updatedUser.setFirstName("Diana");
//		updatedUser.setLastName("Rider");
//
//		log.info("Updated user: {}", userService.updateUser("diana.rider@beeg.com", updatedUser));

		//==============================================================================================================

		//==============================================================================================================
//		// update address
//		// create a sample dto for updating an address
//		AddressDto updatedAddress = new AddressDto(10, "n3", "345 Cypress St", "", "San Jose", "CA", 95101, "USA");
//		AddressDto saved = userService.updateAddress("diana.rider@beeg.com", 352, updatedAddress);
//		log.info("Updated address: {}", saved);
		//==============================================================================================================

		//==============================================================================================================
		// partial update address (pass null and 0 to the fields that you don't want to update)
//		AddressDto updatedAddress = new AddressDto(0, null, null, null, "Dystopian", null, 0, "AZR");
//		userService.updateAddress("diana.rider@beeg.com", 402, updatedAddress);
		//==============================================================================================================

		//==============================================================================================================
		// delete user
//		userService.deleteUser("dani.daniels@beeg.com");
		//==============================================================================================================

		//==============================================================================================================
		// delete address
//		userService.deleteAddress("diana.rider@beeg.com", 352);
		//==============================================================================================================
	}

}
