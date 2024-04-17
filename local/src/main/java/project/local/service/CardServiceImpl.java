package project.local.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.local.dto.CardDetailDTO.CardDetailDTO;
import project.local.dto.local.LocalCardBenefitsDTO;
import project.local.entity.cardInfo.Card;
import project.local.entity.cardInfo.CardBenefits;
import project.local.repository.CardBenefitsRepository;
import project.local.repository.CardRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CardServiceImpl {

    private final CardBenefitsRepository cardBenefitsRepository;
    private final CardRepository cardRepository;

    // 혜택 데이터 전부 불러오기
    public List<LocalCardBenefitsDTO> findAllCardBenefit() {
        List<CardBenefits> cardBenefits = cardBenefitsRepository.findAll();

        List<LocalCardBenefitsDTO> localCardBenefitsDTOS = new ArrayList<>();

        for (CardBenefits benefit : cardBenefits) {
            LocalCardBenefitsDTO localCardBenefitsDTO = LocalCardBenefitsDTO.builder()
                    .id(benefit.getId())
                    .category(benefit.getCategory())
                    .cardType(benefit.getCard().getCardType())
                    .cardId(benefit.getCard().getId()).build();

            localCardBenefitsDTOS.add(localCardBenefitsDTO);
        }


        return localCardBenefitsDTOS;
    }

    public CardDetailDTO findById(Long id) {
        CardBenefits a = cardBenefitsRepository.findById(id).orElse(null);
        Card b = cardRepository.findById(a.getCard().getId()).orElse(null);

        return CardDetailDTO.builder()
                .benefits_Id(a.getId())
                .card_benefit_title(a.getBenefitTitle())
                .card_benefit_summary(a.getBenefitSummary())
                .card_company(b.getCardCompany())
                .card_img(b.getCardImage())
                .build();

    }
}
