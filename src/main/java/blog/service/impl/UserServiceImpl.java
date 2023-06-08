package blog.service.impl;

import blog.Model.User;
import blog.repository.IUserRepository;
import blog.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {
    IUserRepository userRepository;

    @Autowired
    public UserServiceImpl(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public Optional<User> findById(Long aLong) {
        return this.userRepository.findById(aLong);
    }

    @Override
    public Optional<User> findUserByUserNameAndPassword(String username, String password) {
        Optional<User> user = this.userRepository.findUserByUsernameAndPassword(username,password);
        if (user.isPresent()) {
            return user;
        } else {
            return Optional.empty();
        }
    }

    @Override
    public User save(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public boolean existsUserByUsername(String username) {
        return this.userRepository.existsUserByUsername(username);
    }
}
