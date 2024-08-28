
# User

### UserService

Service responsible for following actions:  

- Getting the details of a user
- Creating a new user
- Editing the user details fully and partially
- Get list of all addresses of a user
- Add new address to a user
- Edit address of a user fully and partially
- Delete address of a user

**UserRepository**: Responsible for managing userEntity details only.  
**AddressRepository**: Responsible for managing addresses only.

The UserService will be the only exposed service to the presentation layer.  
The UserService will interact with the UserRepository and AddressRepository to manage the required data.  

#### Constraints:

- A user cannot be created without an address. 
- A user can have multiple addresses.
- Addresses can be deleted after creation of user.

---

### Methods

- `UserDto getUserByEmail(String email)`  
- `List<AddressDto> getAllAddressesByEmail(String email)`  
- `UserDto saveNewUser(UserDto newUser, AddressDto newAddress)`  
- `void saveNewAddress(String email, AddressDto newAddress)`  
- `UserDto updateUser(String email, UserDto updatedUser)` - for partial update leave the fields that you don't want to update as null
- `AddressDto updateAddress(String email, long addressId, AddressDto newAddress)` - pass null(Object) and 0(int, long) to the fields that you don't want to update  
- `void deleteUser(String email)`  
- `void deleteAddress(String email, long addressId)`   

