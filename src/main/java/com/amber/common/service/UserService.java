package com.amber.common.service;

import com.amber.common.entity.User;
import java.util.List;

public interface UserService {

    List<User> getInfo();

    User getUserInfo();

}
