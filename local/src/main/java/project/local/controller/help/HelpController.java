package project.local.controller.help;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.local.dto.loginAndSingUp.CustomUserDetails;
import project.local.dto.mypage.HelpDTO;
import project.local.service.UserServiceImpl;

import javax.servlet.http.HttpSession;
import java.util.List;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/universe/csmyq")
public class HelpController {

    private final UserServiceImpl userService;
    @GetMapping
    public ResponseEntity<List<HelpDTO>> findHelps(HttpSession session) {
        CustomUserDetails sessionUser = (CustomUserDetails) session.getAttribute("USER");
            Long userId = Long.valueOf(sessionUser.getUsername());
            List<HelpDTO> helps = userService.findHelps(userId);
            return ResponseEntity.ok(helps);

    }
    @PostMapping("/add")
    public ResponseEntity<?> requestHelp(@RequestBody HelpDTO helpDTO,HttpSession session) {
        CustomUserDetails sessionUser = (CustomUserDetails) session.getAttribute("USER");
        Long userId = Long.valueOf(sessionUser.getUsername());
        userService.saveHelp(helpDTO, userId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/select/{questionId}")
    public HelpDTO findHelp(@PathVariable("questionId") Long id) {
        return userService.findHelp(id);
    }

    @DeleteMapping("/delete/{questionId}")
    public void deleteHelp(@PathVariable("questionId") Long id) {
        userService.deleteHelp(id);
    }
}
