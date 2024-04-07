package project.local.dto;

import lombok.*;

import java.util.List;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MypageDTO {

    // 구독 서비스 추천을 위한 분류
    // 어떤 구독 서비스를 넣을지 카테고리는 어떻게 나눌지 정하면 편할듯
    private int totalAmount;
    private int restaurant;
    private int cafe;
    private int gasStation;
//    private final int clothes;
    private int etc;

    private List<String> images;
}
