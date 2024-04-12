package project.local.dto.mypage;

import lombok.*;

@Builder
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RecommendedSubDTO {

    private String category;
    private String title;
    private String summary;
    private String fee;
    private String cardCompanyImage;
    private String companyImage;
}
