package project.local.controller.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.local.dto.local.LocalCardDTO;
import project.local.service.AdminServiceImpl;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/universe/admin")
public class AdminController {

    private final AdminServiceImpl adminService;

    @PostMapping
    public void getCardInfos(@RequestBody LocalCardDTO localCardDTO) {
        adminService.updateCard(localCardDTO);
        adminService.updateBenefits(localCardDTO);
    }
}
