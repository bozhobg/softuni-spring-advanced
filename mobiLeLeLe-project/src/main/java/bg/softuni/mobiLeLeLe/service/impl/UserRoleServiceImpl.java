package bg.softuni.mobiLeLeLe.service.impl;

import bg.softuni.mobiLeLeLe.repository.UserRoleRepository;
import bg.softuni.mobiLeLeLe.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImpl implements UserRoleService {
    private final UserRoleRepository userRoleRepository;

    @Autowired
    public UserRoleServiceImpl(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }


}
