package com.example.readygo.service;

import com.example.readygo.model.User;
import com.example.readygo.repository.UserRepository;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements  IUserService{
    private final UserRepository userRepository;
    @Override
    public String addUser(User user) throws IOException {
        if (user.getUser_email() == null || user.getUser_age() == 0 || user.getUser_password() == null){
            return "nah, one of the fields is invalid";
        }
        String currentEmail = user.getUser_email();
        if (userRepository.existEmail(currentEmail)){
            return "nah, the email has been taken";
        }
        if (!isValidPassword(user.getUser_password())){
            return "nah, the password is invalid";
        }
        IPInfo userInfo = getIP();
        user.setUser_latitude(userInfo.getLatitude());
        user.setUser_longitude(userInfo.getLongitude());
        userRepository.save(user);
        return "successfully";
    }

    @Override
    public String checkUser(String email, String password) throws IOException {
        if (!userRepository.existEmail(email)){
            return "email doesn't exist";
        }
        if (!userRepository.isEmailAndPasswordMatch(email, password)){
            return "not matching password";
        }
        User user = userRepository.findUserByEmail(email);
        IPInfo userInfo = getIP();
        assert userInfo != null;
        user.setUser_longitude(userInfo.getLongitude());
        user.setUser_latitude(userInfo.getLatitude());
        userRepository.save(user);
        return "logged in successfully";
    }

    @Override
    public boolean updateUser(Long userId, User user) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()){
            User currUser = userOptional.get();
            currUser.setUser_email(user.getUser_email());
            currUser.setUser_age(user.getUser_age());
            currUser.setUser_name(user.getUser_name());
            currUser.setUser_password(user.getUser_password());
            userRepository.save(currUser);
            return true;
        }
        return false;
    }

    //get the coordinates of the user
    public static IPInfo getIP() throws IOException {
//        String ipAddress = request.getRemoteAddr();
        String ipAddress = "203.194.54.7";
        String apiURL = "https://api.ip2location.io/?key=AC30345A00814669A9B01176665CF22D" + "&ip="+ ipAddress;

        RestTemplate restTemplate = new RestTemplate();

        // Make the HTTP GET request and get the response directly
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(apiURL, String.class);

        // Check if the request was successful (HTTP status code 200)
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            // Get and print the JSON response
            String jsonResponse = responseEntity.getBody();
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(jsonResponse, IPInfo.class);
        } else {
            System.out.println("HTTP Request Failed with response code: " + responseEntity.getStatusCodeValue());
        }
        return null;
    }
    @AllArgsConstructor
    @NoArgsConstructor
    @Setter
    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    static class IPInfo {
        private double latitude;
        private double longitude;

    }

    // check the password
    public static boolean isValidPassword(String password) {
        String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$";
        return password.matches(passwordRegex);
    }
}
