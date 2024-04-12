package project.local.dto.mypage;

import lombok.*;

import java.util.List;

@Builder
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MypageDTO {

    private TimeAndTotalAmountDTO timeAndTotalAmountDTO;

    private SpentAmountDTO spentAmountDTO;
    // 내 카드 리스트
    private List<String> images;

    private List<RecommendedSubDTO> recommendedSubDTO;

    private List<MySubscriptionDTO> mySubscriptionDTO;

}
