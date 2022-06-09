package com.proit.todoapp.repositories;

import com.proit.todoapp.entity.Authority;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
/**
 * Created by rana on 9/06/22.
 */
public interface AuthorityRepository extends PagingAndSortingRepository<Authority, Integer> {

    @Query("SELECT x FROM Authority x WHERE x.username = :username AND x.authority = :authority")
    public Authority findByUsernameAndAuthority(@Param("username") String username, @Param("authority") String authority);

    @Query("SELECT x FROM Authority x WHERE x.username = :username")
    public List<Authority> findAllByUsername(@Param("username") String username);
}
