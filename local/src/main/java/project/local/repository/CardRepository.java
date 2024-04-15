package project.local.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.local.entity.cardInfo.Card;

public interface CardRepository extends JpaRepository<Card, Long> {
}
