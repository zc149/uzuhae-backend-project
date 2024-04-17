package project.local.service.grading;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import project.local.dto.posDTO.PosDTO;
import project.local.entity.userInfo.User;
import project.local.repository.mypage.UserRepository;

@RequiredArgsConstructor
@Service
public class GradingService  {
    private final UserRepository userRepository;


    public void updateTransaction(PosDTO posDTO) {
        User user = userRepository.findById(posDTO.getId()).orElse(null);
        int updateRealPay = user.getRealPay() + posDTO.getRealPay();
        int updateResultPay = user.getResultPay() + posDTO.getResultPay();
        user.setRealPay(updateRealPay);
        user.setResultPay(updateResultPay);
        userRepository.save(user);
    }
}
