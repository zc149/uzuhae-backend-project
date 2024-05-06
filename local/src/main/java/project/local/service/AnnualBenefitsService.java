package project.local.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.local.entity.userInfo.AnnualBenefits;

@Service
@RequiredArgsConstructor
public class AnnualBenefitsService {

    private final AnnualBenefitsService annualBenefitsService;

    public AnnualBenefits findById(Long id) {
        return annualBenefitsService.findById(id);
    }
}
