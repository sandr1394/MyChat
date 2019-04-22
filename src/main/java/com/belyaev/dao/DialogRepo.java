package com.belyaev.dao;

import com.belyaev.entity.Dialog;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DialogRepo extends CrudRepository<Dialog, Long> {

    List<Dialog> findAll();
}
