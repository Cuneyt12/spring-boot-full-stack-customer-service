package com.cuneytokmen.service.impl;

import com.cuneytokmen.entity.LoginInformation;
import com.cuneytokmen.repository.ILoginRepository;
import com.cuneytokmen.service.ILoginService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginServiceImpl implements ILoginService {
    private final ILoginRepository iLoginRepository;

    public LoginServiceImpl(ILoginRepository iLoginRepository) {
        this.iLoginRepository = iLoginRepository;
    }

    @Override
    public LoginInformation getLoginInformationByUsername(String username) {
        return iLoginRepository.getLoginInformationByUsername(username);
    }

}
