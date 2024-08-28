
### Fetching both users and address

Fetching a user also brings in the list of associated addresses. Might lead to performance issues if the number of addresses is large.  
> *How to fetch only the user without the addresses?*

### Mapping non null fields of a dto to an entity

When mapping a DTO to an entity, only the non-null fields of the DTO should be mapped to the entity.
> *How to achieve this?*  
> *How to map a dto to an entity passed as a parameter to the mapper?*

### LazyInitializationException

failed to lazily initialize a collection of role: com.shockweb.db.domain.entity.UserEntity.address: could not initialize proxy - no Session.
> Fixed with `@Transactional` on the service method.