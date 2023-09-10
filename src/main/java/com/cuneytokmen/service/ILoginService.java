package com.cuneytokmen.service;

import com.cuneytokmen.entity.LoginInformation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ILoginService {
    LoginInformation getLoginInformationByUsername(String username);
}
