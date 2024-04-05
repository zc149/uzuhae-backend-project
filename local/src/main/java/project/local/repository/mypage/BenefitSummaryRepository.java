package project.local.repository.mypage;

import org.springframework.data.jpa.repository.JpaRepository;
import project.local.entity.BenefitSummary;

import java.util.List;

public interface BenefitSummaryRepository extends JpaRepository<BenefitSummary, Long> {

    List<BenefitSummary> findByBenefitTitle(String benefitTitle);

    // 이게 되나...? dk 이러면 합집합임 gg
//    List<BenefitSummary> findAllByBenefitTitle(List<String> benefits);
}

