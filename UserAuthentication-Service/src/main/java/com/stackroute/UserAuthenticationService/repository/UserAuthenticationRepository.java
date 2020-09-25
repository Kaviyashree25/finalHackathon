package com.stackroute.UserAuthenticationService.repository;

import com.stackroute.UserAuthenticationService.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAuthenticationRepository extends JpaRepository<User, String> {
    User findByUserIdAndUserPassword(String userId, String userPassword);
}
