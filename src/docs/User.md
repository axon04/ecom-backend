
# User

### UserService

Service responsible for following actions:  

- Getting the details of a userEntity
- Editing the userEntity details 
- Add new userEntity address
- Delete userEntity address
- Changing default address
- Getting default address
- Get list of all address

**UserRepository**: Responsible for managing userEntity details only.  
**AddressRepository**: Responsible for managing addresses only.  
**UserAddressRepository**: Responsible for mapping addresses to a userEntity.

The UserService will be the only exposed service to the presentation layer.  
The AddressService will be responsible for adding, editing, removing and mapping the addresses to a userEntity. The AddressService will use both AddressRepository and UserAddressRepository.  

#### Constraints:

- A userEntity cannot be created without an address.
- A userEntity can delete the address after creation, but will require address when ordering.

### DTO

- UserDTO: UserEntity + Default Address
- AddressDTO: UserId + List of Addresses

---

### Methods

- `getUserByUsername(String username)`  
- `getDefaultAddress(String username)`  
- `getAllAddresses(String username)`  
- `setNewUser(UserDTO newUser)`  
- `setNewAddress(AddressDTO newAddress)`  

