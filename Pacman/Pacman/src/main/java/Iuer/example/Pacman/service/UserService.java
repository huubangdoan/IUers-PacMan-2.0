package Iuer.example.Pacman.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Iuer.example.Pacman.entity.User;
import Iuer.example.Pacman.repository.UserRepository;
import Iuer.example.Pacman.dto.AuthRequest;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public String register(AuthRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            return "Username already exists!";
        }
        User newUser = new User();
        newUser.setUsername(request.getUsername());
        newUser.setPassword(request.getPassword()); // Sau này Huyền Trang sẽ gắn mã hóa BCrypt/JWT vào đây
        userRepository.save(newUser);
        return "User registered successfully!";
    }

    public boolean login(AuthRequest request) {
        Optional<User> user = userRepository.findByUsername(request.getUsername());
        if (user.isPresent()) {
            return user.get().getPassword().equals(request.getPassword());
        }
        return false;
    }
}