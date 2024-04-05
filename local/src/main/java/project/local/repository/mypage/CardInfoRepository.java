package project.local.repository.mypage;

import org.springframework.data.jpa.repository.JpaRepository;
import project.local.entity.CardInfo;

import java.util.List;

public interface CardInfoRepository extends JpaRepository<CardInfo, Long> {

    List<CardInfo> findByCardType(String cardType);
//    List<CardInfo> findByUserInfo_Id(int userId);
}
