package project.local.service.carddetail;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.local.dto.CardDetailDTO.CardDetailDTO;
import project.local.entity.cardInfo.Card;
import project.local.entity.cardInfo.CardBenefits;
import project.local.repository.CardBenefitsRepository;
import project.local.repository.CardRepository;

@RequiredArgsConstructor
@Service
public class CardDetailService {
    private final CardRepository cardRepository;
    private final CardBenefitsRepository cardBenefitsRepository;

    public CardDetailDTO findById(Long id) {
        CardBenefits a = cardBenefitsRepository.findById(id).orElse(null);
        Card b = cardRepository.findById(a.getCard().getId()).orElse(null);

        return CardDetailDTO.builder()
                .benefits_Id(a.getId())
                .card_benefit_title(a.getBenefitTitle())
                .card_benefit_summary(a.getBenefitSummary())
                .card_company(b.getCardCompany().getId())
                .card_img(b.getCardImage())
                .build();

    }

}
