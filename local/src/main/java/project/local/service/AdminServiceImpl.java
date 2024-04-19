package project.local.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import project.local.dto.CardDetailDTO.CardDetailDTO;
import project.local.dto.local.LocalCardBenefitsDTO;
import project.local.dto.local.LocalCardDTO;
import project.local.entity.cardInfo.Card;
import project.local.entity.cardInfo.CardBenefits;
import project.local.repository.CardBenefitsRepository;
import project.local.repository.CardRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl {

    private final CardRepository cardRepository;
    private final CardBenefitsRepository cardBenefitsRepository;

    @Transactional
    public void updateCard(LocalCardDTO localCardDTO) {
        cardRepository.findById(localCardDTO.getId()).ifPresent(card -> {
            Optional.ofNullable(localCardDTO.getCardImage()).ifPresent(card::setCardImage);
            Optional.ofNullable(localCardDTO.getCardCompany()).ifPresent(card::setCardCompany);
            Optional.ofNullable(localCardDTO.getCardName()).ifPresent(card::setCardName);
            Optional.ofNullable(localCardDTO.getAnnualFee()).ifPresent(card::setAnnualFee);
            Optional.ofNullable(localCardDTO.getPreviousAmount()).ifPresent(card::setPreviousAmount);
            Optional.ofNullable(localCardDTO.getCardType()).ifPresent(card::setCardType);
        });
    }

    @Transactional
    public void updateBenefits(LocalCardDTO localCardDTO) {
        List<CardBenefits> byCardId = cardBenefitsRepository.findByCard_Id(localCardDTO.getId());
        for (CardBenefits cardBenefits : byCardId) {
            for (CardDetailDTO benefit : localCardDTO.getBenefits()) {
                if (benefit.getBenefitTitle() != null) {
                    cardBenefits.setBenefitTitle(benefit.getBenefitTitle());
                }
                if (benefit.getBenefitSummary() != null) {
                    cardBenefits.setBenefitSummary(benefit.getBenefitSummary());
                }
            }
        }
    }
}