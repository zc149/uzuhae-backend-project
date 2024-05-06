package project.local.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.local.entity.userInfo.AnnualBenefits;
import project.local.entity.userInfo.User;
import project.local.repository.AnnualBenefitsRepository;

@Service
@RequiredArgsConstructor
public class AnnualBenefitsService {

    private final AnnualBenefitsRepository annualBenefitsRepository;

    public AnnualBenefits findById(Long id) {
        return annualBenefitsRepository.findById(id).orElse(null);
    }
}
