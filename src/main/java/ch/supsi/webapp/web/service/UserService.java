package ch.supsi.webapp.web.service;

import ch.supsi.webapp.web.model.User;
import ch.supsi.webapp.web.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    public List<User> get() {
        return this.userRepository.findUsers();
    }

    public Optional<User> getUser(@PathVariable long id) {
        return this.userRepository.findUserById(id);
    }

    public Optional<User> getUser(@PathVariable String username) {
        return this.userRepository.findUserByUsername(username);
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = getUser(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        System.out.println(user.get().getRole());
        List<GrantedAuthority> auth = AuthorityUtils.createAuthorityList(user.get().getRole().toString());
        return new org.springframework.security.core.userdetails.User(username, user.get().getPassword(), true, true, true, true, auth);
    }
}
