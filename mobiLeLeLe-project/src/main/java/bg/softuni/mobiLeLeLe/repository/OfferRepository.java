package bg.softuni.mobiLeLeLe.repository;

import bg.softuni.mobiLeLeLe.model.entity.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {
    @Query(
        "select o.seller.id from Offer o where o.id = :offerId"
    )
    Long findSellerIdByOfferid(@Param("offerId") Long offerId);

}
