package com.travelplanner.core.user.domain.port.out;

import com.travelplanner.core.user.domain.model.UserModel;

public interface UserEventPort {

    public void sendRegisterEvent(UserModel user);
}
