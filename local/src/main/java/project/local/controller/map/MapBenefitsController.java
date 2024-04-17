package project.local.controller.map;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import project.local.dto.map.MapBenefitDTO;
import project.local.service.MapServiceImpl;

@RestController
@RequiredArgsConstructor
public class MapBenefitsController {
    private final MapServiceImpl cardBenefitsService;

    @GetMapping("universe/map")
    public MapBenefitDTO getCardBenefitsByCategory(@RequestParam String category, Long id) {

        try {
            String categoryByCode = cardBenefitsService.getCategoryByCode(category);
            return cardBenefitsService.findCardByCategory(categoryByCode, id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
