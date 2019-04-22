package com.belyaev.dao;

import com.belyaev.entity.UserDialogLink;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.math.BigInteger;
import java.util.List;

public interface UserDialogLinkRepo extends CrudRepository<UserDialogLink, Long> {

    @Query(nativeQuery = true, value = "select user_id from user_dialog_link  udl where udl.dialog_id =:param")
    List<BigInteger> findUsersIdByDialogId(@Param("param") Long param);

    @Query(nativeQuery = true, value = "select dialog_id from user_dialog_link udl where udl.user_id =:param")
    List<BigInteger> findDialogsByUserId(@Param("param") Long param);

}
