package com.example.htpt.service.Impl;

import com.example.htpt.entity.dto.UserDto;
import com.example.htpt.share.request.ChangePasswordRequest;
import com.example.htpt.share.request.LoginRequest;
import com.example.htpt.share.request.RegisterRequest;
import com.example.htpt.entity.User;
import com.example.htpt.entity.enums.Role;
import com.example.htpt.mapper.UserMapper;
import com.example.htpt.repository.UserRepository;
import com.example.htpt.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public UserDto findByUsername(String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        return userOptional.map(UserMapper.MAPPER::mapToUserDto).orElse(null);
    }

    @Override
    public void save(UserDto userDto) {
        User updatedUser = UserMapper.MAPPER.mapToUser(userDto);
        userRepository.save(updatedUser);
    }

    @Override
    public UserDto login(LoginRequest request) {
        Optional<User> userOptional = userRepository.findByUsername(request.getUsername());
        if (userOptional.isPresent() && passwordEncoder.matches(request.getPassword(), userOptional.get().getPassword())) {
            return UserMapper.MAPPER.mapToUserDto(userOptional.get());
        }
        return null;
    }

    @Override
    public UserDto register(RegisterRequest request) throws Exception{
        Optional<User> existedUser = userRepository.findByUsername(request.getUsername());
        if (existedUser.isPresent()) {
            throw new Exception("Username is existed!");
        }
        existedUser = userRepository.findByEmail(request.getEmail());
        if (existedUser.isPresent()) {
            throw new Exception("Email is existed!");
        }
        User newUser = User.builder().
                username(request.getUsername()).
                password(passwordEncoder.encode(request.getPassword())).
                fullName(request.getFullName()).
                email(request.getEmail()).
                dob(request.getDob()).
                gender(request.getGender()).
                role(Role.CUSTOMER).
                phoneNumber(request.getPhoneNumber()).
                address(request.getAddress()).
                build();
        userRepository.save(newUser);
        return UserMapper.MAPPER.mapToUserDto(newUser);
    }

    @Override
    public boolean changePassword(String username, ChangePasswordRequest request) throws Exception{
        Optional<User> existedUser = userRepository.findByUsername(username);
        if (existedUser.isPresent()) {
            String oldPassword = request.getOldPassword();
            String newPassword = request.getNewPassword();
            String retypeNewPassword = request.getRetypeNewPassword();

            if (!passwordEncoder.matches(oldPassword, existedUser.get().getPassword())) {
                throw new Exception("oldPassword is wrong!");
            }
            if (newPassword.equals(retypeNewPassword)) {
                throw new Exception("newPassword and retypeNewPassword aren't match");
            }
            existedUser.get().setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(existedUser.get());
        }
        return true;
    }

    @Override
    public void createCustomer(UserDto userDto) {
        User user = UserMapper.MAPPER.mapToUser(userDto);
        user.setRole(Role.CUSTOMER);
        userRepository.save(user);
    }

    @Override
    public List<UserDto> getAllCustomer() {
        List<User> customers = userRepository.getCustomers();
        return customers.stream().map(UserMapper.MAPPER::mapToUserDto).toList();
    }

    @Override
    public UserDto getCustomerByUserId(String userId) {
        Optional<User> user = userRepository.findById(userId);
        return user.map(UserMapper.MAPPER::mapToUserDto).orElse(null);
    }

    @Override
    public void deleteByUserId(String userId) {
        userRepository.deleteById(userId);
    }
}
