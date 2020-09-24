package mk.ukim.finki.rentscoot.repository;

import mk.ukim.finki.rentscoot.model.Promotion;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PromotionRepository {
    Promotion createPromotion(Promotion promotion);

    List<Promotion> getAllPromotions();

    Optional<Promotion> findById(String name);

    void deletePromotion(Promotion promotion);

    List<Promotion> findBestDiscountPromotions(double discount);

    List<Promotion> findAllValidPromotions(LocalDate dateOfSubmition);

    boolean exists(String name);
}
