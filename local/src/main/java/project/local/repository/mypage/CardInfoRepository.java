package project.local.repository.mypage;

import org.springframework.data.jpa.repository.JpaRepository;
import project.local.entity.Card;

import java.util.List;

public interface CardInfoRepository extends JpaRepository<Card, Long> {

    List<Card> findByCardType(String cardType);
//    List<CardInfo> findByUserInfo_Id(int userId);
}
