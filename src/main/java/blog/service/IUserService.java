package blog.service;

import blog.Model.User;

import java.util.Optional;

public interface IUserService extends ICoreService<User,Long>{
    Optional<User> findUserByUserNameAndPassword(String username,String password);

    boolean existsUserByUsername(String username);
}

