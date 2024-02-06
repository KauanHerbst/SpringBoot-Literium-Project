package com.herbst.projectspringtwo.repositories;

import com.herbst.projectspringtwo.entities.Book;
import com.herbst.projectspringtwo.entities.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

}
