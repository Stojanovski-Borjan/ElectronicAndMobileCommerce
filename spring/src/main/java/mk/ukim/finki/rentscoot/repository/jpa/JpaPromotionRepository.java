package mk.ukim.finki.rentscoot.repository.jpa;

import mk.ukim.finki.rentscoot.model.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface JpaPromotionRepository extends JpaRepository<Promotion,String> {
    List<Promotion> findByDiscountGreaterThanOrderByDiscountDesc(double discount);

    List<Promotion> findAllByValidFromBeforeOrderByDiscountDesc(LocalDate startReservationDate);

}
