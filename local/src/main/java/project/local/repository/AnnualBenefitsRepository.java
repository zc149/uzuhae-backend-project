package project.local.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.local.entity.userInfo.AnnualBenefits;


import java.math.BigInteger;

@Repository
public interface AnnualBenefitsRepository extends JpaRepository<AnnualBenefits, Long> {

}
