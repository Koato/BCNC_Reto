package com.kato.bcnc.repository;

import com.kato.bcnc.domain.Price;
import com.kato.bcnc.domain.PriceId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface PriceRepository extends JpaRepository<Price, PriceId> {

    @Query("""
      SELECT p FROM Price p
       WHERE p.id.brandId = :brandId
         AND p.id.productId = :productId
         AND :applicationDate BETWEEN p.id.startDate AND p.id.endDate
       ORDER BY p.priority DESC
      """)
    Optional<Price> findApplicablePrice(
            @Param("brandId") Integer brandId,
            @Param("productId") Integer productId,
            @Param("applicationDate") LocalDateTime applicationDate
    );
}
