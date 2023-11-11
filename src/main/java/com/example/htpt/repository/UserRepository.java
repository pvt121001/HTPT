package com.example.htpt.repository;

import com.example.htpt.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    @Query(value = "select u from User u where u.role = 'CUSTOMER'")
    List<User> getCustomers();

    @Query(value = "select u from User u where u.role = 'EMPLOYEE'")
    List<User> getEmployees();
}
