package com.proit.todoapp.repositories;

import com.proit.todoapp.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
/**
 * Created by rana on 9/06/22.
 *
 */
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    @Query("SELECT x FROM User x WHERE x.username = :username ")
    public User findByUsername(@Param("username") String username);
	
}
