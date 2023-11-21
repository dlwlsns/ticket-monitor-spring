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

    public Optional<User> getUser(@PathVariable String id) {
        return this.userRepository.findUserById(id);
    }

    public Optional<User> post(@RequestBody User user){
        return Optional.of(userRepository.save(user));
    }

    public Optional<User> put(@RequestBody User user, @PathVariable String id){
        Optional<User> currentUser = userRepository.findById(id);

        if (currentUser.isPresent()) {
            User newUser = currentUser.get();
            newUser.setName(user.getName());
            newUser.setSurname(user.getSurname());
            newUser.setSurname(user.getUsername());

            currentUser = Optional.of(newUser);

            this.delete(id);
            this.post(newUser);
        }

        return currentUser;
    }

    public boolean delete(@PathVariable String id) {
        this.userRepository.deleteById(id);

        Optional<User> userData = userRepository.findById(id);
        return userData.isEmpty();
    }
}
