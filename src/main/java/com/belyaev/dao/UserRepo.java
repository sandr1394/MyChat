package com.belyaev.dao;

import com.belyaev.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepo extends CrudRepository<User, Long> {

    List<User> findAll();

    User findUserByNik(String nik);

}
