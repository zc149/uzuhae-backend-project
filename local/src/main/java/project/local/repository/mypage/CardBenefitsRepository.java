package project.local.repository.mypage;

import org.springframework.data.jpa.repository.JpaRepository;
import project.local.entity.cardInfo.CardBenefits;

public interface CardBenefitsRepository extends JpaRepository<CardBenefits, Long> {
}
