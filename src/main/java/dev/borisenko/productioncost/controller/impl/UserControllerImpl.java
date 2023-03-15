package dev.borisenko.productioncost.controller.impl;

import dev.borisenko.productioncost.controller.UserController;
import dev.borisenko.productioncost.model.Cost;
import dev.borisenko.productioncost.model.User;
import dev.borisenko.productioncost.payload.response.MessageResponse;
import dev.borisenko.productioncost.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class UserControllerImpl implements UserController {
    private final UserService userService;
    private final String ERR_MSG = "Не удалось найти пользователя, id: ";
    private final String SUC_MSG = "Данные пользователя успешно обновлены";

    @Autowired
    public UserControllerImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getById(int id) {
        Optional<User> t = userService.getById(id);

        if (t.isPresent()) {
            return new ResponseEntity<>(t.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new MessageResponse(ERR_MSG + id), HttpStatus.NO_CONTENT);
        }
    }

    @Override
    public ResponseEntity<?> add(User user) {
        userService.create(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> delete(int id) {
        if (userService.exist(id)) {
            userService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new MessageResponse(ERR_MSG + id), HttpStatus.NO_CONTENT);
        }
    }

    @Override
    public ResponseEntity<?> update(int id, User user) {
        if (userService.exist(id)) {
            user.setId(id);
            userService.save(user);
            return new ResponseEntity<>(new MessageResponse(SUC_MSG), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new MessageResponse(ERR_MSG + id), HttpStatus.NO_CONTENT);
        }
    }
}
