package dev.borisenko.productioncost.service;

import dev.borisenko.productioncost.model.CostItem;
import dev.borisenko.productioncost.model.Unit;
import dev.borisenko.productioncost.model.User;
import dev.borisenko.productioncost.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService /*implements UserDetailsService*/ {
    private final UserRepo userRepo;
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public void create(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepo.save(user);
    }

    public List<User> getAll() {
        return userRepo.findAll();
    }

    /*public User getUserByUsername(String username) {
        return userRepo.getByUsername(username);
    }

    public boolean isPresent(User user) {
        Optional<User> existingUser = userRepo.findByUsername(user.getUsername());
        return existingUser.isPresent() ? true : false;
    }*/

    public Optional<User> getById(int id) {
        return userRepo.findById(id);
    }

    public boolean exist(int id) {
        return userRepo.existsById(id);
    }

    public void save(User user) {
        userRepo.save(user);
    }

    public void delete(int id) {
        userRepo.deleteById(id);
    }

    /*@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException(
                        String.format("USER_NOT_FOUND", username)
                )
        );
    }*/
}
