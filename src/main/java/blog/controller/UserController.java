package blog.controller;

import blog.Model.User;
import blog.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RequestMapping
@RestController
public class UserController {
    private final IUserService userService;
    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }
    @PostMapping("/users/auth")
    public ResponseEntity<User> checkLogin(@RequestBody User user){
        String username = user.getUsername();
        String password = user.getPassword();
        Optional<User> validUser = this.userService.findUserByUserNameAndPassword(username,password);
        if (validUser.isPresent()) {
            return new ResponseEntity<>(validUser.get(),HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new User(),HttpStatus.OK);
        }
    }
    @GetMapping("/users/{id}")
    public ResponseEntity<User> findUserById(@PathVariable Long id) {
        return new ResponseEntity<>(this.userService.findById(id).get(),HttpStatus.OK);
    }
    @GetMapping("/users/{username}/exists")
    public ResponseEntity<Boolean> checkUsernameExists(@PathVariable String username) {
        boolean exits = this.userService.existsUserByUsername(username);
        return new ResponseEntity<>(exits,HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity<User> create(@RequestBody User user) {
        return new ResponseEntity<>(this.userService.save(user),HttpStatus.CREATED );
    }
}
