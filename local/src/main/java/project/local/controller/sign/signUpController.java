package project.local.controller.sign;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import project.local.dto.loginAndJoin.UserDTO;
import project.local.service.inter.JoinService;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173/")
public class signUpController {

    private final JoinService joinService;

    @PostMapping("/api/signUp")
    public String singUp (@RequestBody UserDTO userDTO) {
        System.out.println(userDTO.getId());
        joinService.signUp(userDTO);

        return "ok";
    }

}
