package com.accelhack.application.api.shared.transaction;

import com.accelhack.accelparts.Request;
import com.accelhack.accelparts.ResponseSet;
import com.accelhack.accelparts.response.ListResponse;
import com.accelhack.application.api.shared.model.User;
import com.accelhack.application.api.shared.model.UserSelector;

public interface UserTransaction {

  ResponseSet<ListResponse<User>> search(Request<UserSelector> userRequest);
}