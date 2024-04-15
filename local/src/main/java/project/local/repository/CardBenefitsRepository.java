package project.local.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.local.entity.cardInfo.CardBenefits;

public interface CardBenefitsRepository extends JpaRepository<CardBenefits, Long> {
}
