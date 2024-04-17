package project.local.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import project.local.dto.loginAndJoin.UserDTO;
import project.local.entity.userInfo.User;
import project.local.repository.mypage.UserRepository;
import project.local.service.inter.JoinService;

import java.sql.Date;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class signUpServiceimpl implements JoinService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void signUp(UserDTO userDTO) {
        User data = User.builder()
                .id(userDTO.getId())
                .password(bCryptPasswordEncoder.encode(userDTO.getPassword()))
                .nickName(userDTO.getNickName())
                .name(userDTO.getName())
                .joinDate(Date.valueOf(LocalDate.now()))
                .role("ROLE_ADMIN") //
                .build();
        
        Boolean ieExist = userRepository.existsById(userDTO.getId());

        if (ieExist) {
            return;
        }

        userRepository.save(data);

    }
}
