package com.cuneytokmen.repository;

import com.cuneytokmen.entity.LoginInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ILoginRepository extends JpaRepository<LoginInformation, Long> {
    LoginInformation getLoginInformationByUsername(String username);
}
