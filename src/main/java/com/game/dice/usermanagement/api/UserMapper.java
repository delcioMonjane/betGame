package com.game.dice.usermanagement.api;

import com.game.dice.usermanagement.model.User;

public class UserMapper {
    public static UserDTO mapToDTO(User user) {
        return new UserDTO(user.getId(), user.isEnabled(), user.getUsername(), user.getName());
    }

}
