package com.game.dice.usermanagement.services;

import com.game.dice.usermanagement.model.User;
import com.game.dice.usermanagement.repositories.UserRepository;
import com.game.dice.walletmanagement.domain.Wallet;
import com.game.dice.walletmanagement.repositories.WalletRepository;
import com.game.dice.walletmanagement.services.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private WalletService walletService;
    @Autowired
    private WalletRepository walletRepository;

    public User createUser(String username, String password, String name) {
        User user = User.newUser(username, password, name);
        User savedUser = userRepository.save(user);
        Wallet wallet = walletService.createWallet(savedUser);
        walletRepository.save(wallet);
        return savedUser;
    }

    public User getUserById(Long id) throws ChangeSetPersister.NotFoundException {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new ChangeSetPersister.NotFoundException();
        }
        return optionalUser.get();
    }

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException(String.format("User with username - %s, not found", username)));
    }

    public Optional<User> findByUsername(final String username) { return userRepository.findByUsername(username); }

}
