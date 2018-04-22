package com.university.itis.itisapp.repository;


import com.university.itis.itisapp.model.Token;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created on 24.05.17.
 */
@Repository
@Lazy
public interface TokenRepository extends JpaRepository<Token, Long> {

    Token findByUsernameAndEndDateIsNull(String username);

    Token findByUsernameAndTokenAndEndDateIsNull(String username, String token);

}
