package com.accelhack.application.api.shared.transaction;

import com.accelhack.application.api.http.*;
import com.accelhack.application.api.shared.model.User;
import com.accelhack.application.api.shared.model.UserSelector;

public interface UserTransaction {

  ResponseSet<ListResponse<User>> search(Request<UserSelector> userRequest);
}
