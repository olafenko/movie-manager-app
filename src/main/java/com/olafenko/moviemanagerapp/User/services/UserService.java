package com.olafenko.moviemanagerapp.User.services;

import com.olafenko.moviemanagerapp.User.dtos.LoginRequest;
import com.olafenko.moviemanagerapp.User.dtos.RegisterRequest;
import com.olafenko.moviemanagerapp.User.dtos.UserDto;
import com.olafenko.moviemanagerapp.User.mappers.UserMapper;
import com.olafenko.moviemanagerapp.User.model.User;
import com.olafenko.moviemanagerapp.User.repo.UserRepository;
import com.olafenko.moviemanagerapp.exception.EmailTakenException;
import com.olafenko.moviemanagerapp.exception.UserNotFoundException;
import com.olafenko.moviemanagerapp.exception.WrongPasswordException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final String USER_NOT_FOUND_MSG = "User with email %email doesn't exist.";
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public UserDto login(LoginRequest request){

        String email = request.email();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));

        if (passwordEncoder.matches(request.password(),user.getPassword())){
            return userMapper.toUserDto(user);
        }
        throw new WrongPasswordException("Incorrect password.");
    }


    public UserDto register(RegisterRequest request) {

        Optional<User> userByEmail = userRepository.findByEmail(request.email());

        if(userByEmail.isPresent()){
            throw new EmailTakenException("Email already taken.");
        }

        String encodedPassword = passwordEncoder.encode(request.password());

        User buildedUser = User.builder().email(request.email()).firstname(request.firstname()).lastname(request.lastname()).password(encodedPassword).build();
        userRepository.save(buildedUser);

        return userMapper.toUserDto(buildedUser);
    }
}
