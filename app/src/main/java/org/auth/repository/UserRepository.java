package org.auth.repository;

import org.auth.entities.UserInfo;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserInfo,Long> {
    UserInfo findByUsername(String username);

}
