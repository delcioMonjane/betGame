package com.game.dice.auth.api;


import com.game.dice.usermanagement.api.UserDTO;
import com.game.dice.usermanagement.api.UserMapper;
import com.game.dice.usermanagement.api.UserRequest;
import com.game.dice.usermanagement.model.User;
import com.game.dice.usermanagement.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/public")
public class AuthController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public UserDTO registerUser(@RequestBody UserRequest userRequest) {
        User user = userService.createUser(userRequest.getUsername(), userRequest.getPassword(), userRequest.getName());
        return UserMapper.mapToDTO(user);
    }
}
