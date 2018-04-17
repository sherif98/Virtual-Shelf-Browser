package com.visual.shelf.demo.db.repository;

import com.visual.shelf.demo.db.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
