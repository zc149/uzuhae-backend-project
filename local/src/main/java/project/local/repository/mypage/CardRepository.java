package project.local.repository.mypage;

import org.springframework.data.jpa.repository.JpaRepository;
import project.local.entity.cardInfo.Card;

public interface CardRepository extends JpaRepository<Card, Long> {
}
