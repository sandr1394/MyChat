package com.belyaev.dao;

import com.belyaev.entity.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepo extends CrudRepository<Message, Long> {

    List<Message> findByDialogId(Long dialogId);
}
