package project.local.dto;

import lombok.*;

import java.util.List;

@Builder
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MypageDTO {

    // 구독 서비스 추천을 위한 분류
    // 어떤 구독 서비스를 넣을지 카테고리는 어떻게 나눌지 정하면 편할듯
    int totalAmount;
    int restaurant;
    int cafe;
    int gasStation;
    int shopping;
    int convenienceStore;
    int supermarket;
    int movie;
    int etc;
    private List<String> images;

}
