package project.local.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.local.entity.cardInfo.CardBenefits;

import java.util.List;

@Repository
public interface CardBenefitsRepository extends JpaRepository<CardBenefits, Long> {
    List<CardBenefits> findByCategory(String benefitTitle);

    List<CardBenefits> findByCard_Id(Long cardId);

    List<CardBenefits> findByBenefitTitle(String benefitTitle);

    CardBenefits findByCard_IdAndCategory(Long cardId, String category);
}
