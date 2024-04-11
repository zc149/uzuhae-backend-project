package project.local.dto;

import lombok.*;

import java.util.List;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SearchDTO {

    private List<Long> cardId;
}
