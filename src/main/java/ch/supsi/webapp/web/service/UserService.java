package ch.supsi.webapp.web.service;

import ch.supsi.webapp.web.model.User;
import ch.supsi.webapp.web.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> get() {
        return this.userRepository.findUsers();
    }

    public Optional<User> getUser(@PathVariable long id) {
        return this.userRepository.findUserById(id);
    }

    public Optional<User> post(@RequestBody User user){
        return Optional.of(userRepository.save(user));
    }

    public Optional<User> put(@RequestBody User user, @PathVariable long id){
        userRepository.save(user);

        Optional<User> currentUser = userRepository.findById(id);

        return currentUser;
    }

    public boolean delete(@PathVariable long id) {
        this.userRepository.deleteById(id);

        Optional<User> userData = userRepository.findById(id);
        return userData.isEmpty();
    }
}
