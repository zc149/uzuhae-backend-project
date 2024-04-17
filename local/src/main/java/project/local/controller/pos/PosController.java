package project.local.controller.pos;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import project.local.dto.posDTO.PosDTO;
import project.local.service.grading.GradingService;


@RestController
@RequiredArgsConstructor
public class PosController {
    private final GradingService gradingService;

    @PostMapping("api/userTransaction")
    public void updateTransaction(@RequestBody PosDTO posDTO) {
        gradingService.updateTransaction(posDTO);
    }
}
