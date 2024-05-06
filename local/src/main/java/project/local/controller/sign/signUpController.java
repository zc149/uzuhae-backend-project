package project.local.controller.sign;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.local.dto.loginAndJoin.UserDTO;
import project.local.entity.userInfo.User;
import project.local.repository.UserRepository;
import project.local.service.CardServiceImpl;
import project.local.service.UserServiceImpl;
import project.local.service.inter.JoinService;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class signUpController {

    private final JoinService joinService;
    private final UserRepository userRepository;

    @PostMapping("/api/signUp")
    public void singUp (@RequestBody UserDTO userDTO) {
        System.out.println(userDTO.getId());
        joinService.signUp(userDTO);


    }

    @GetMapping("/api/user/{userId}")
    public ResponseEntity<UserDTO> getUserInfoById(@PathVariable Long userId) {
        System.out.println("Requested userId: " + userId);
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isPresent()) {
            User user = userOptional.get();
            UserDTO userDTO = new UserDTO();

            userDTO.setNickName(user.getNickName());
            userDTO.setRole(user.getRole());
            return ResponseEntity.ok(userDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



}
