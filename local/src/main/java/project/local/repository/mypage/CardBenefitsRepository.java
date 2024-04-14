package project.local.repository.mypage;

import org.springframework.data.jpa.repository.JpaRepository;
import project.local.entity.cardInfo.CardBenefits;

import java.util.List;

public interface CardBenefitsRepository extends JpaRepository<CardBenefits, Long> {
    List<CardBenefits> findByCategory(String benefitTitle);

    List<CardBenefits> findByCard_Id(Long cardId);

}

