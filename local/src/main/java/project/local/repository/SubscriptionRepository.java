package project.local.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.local.entity.cardInfo.SubscriptionBenefits;

import java.util.List;

@Repository
public interface SubscriptionRepository extends JpaRepository<SubscriptionBenefits, Long> {

    List<SubscriptionBenefits> findByCategory(String maxCategory);
}
