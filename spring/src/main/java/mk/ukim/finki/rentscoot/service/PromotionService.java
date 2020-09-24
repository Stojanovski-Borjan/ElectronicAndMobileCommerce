package mk.ukim.finki.rentscoot.service;

import mk.ukim.finki.rentscoot.model.Promotion;
import org.apache.tomcat.jni.Local;

import java.time.LocalDate;
import java.util.List;

public interface PromotionService {
    Promotion createPromotion(String name, String description, double discount, LocalDate validFrom, LocalDate validTo);

    List<Promotion> getAllPromotions();

    Promotion getPromotion(String name);

    Promotion updatePromotion(String name, String description, double discount, LocalDate validFrom, LocalDate validTo);

    void deletePromotion(String name);

    List<Promotion> findBestDiscountPromotions(double discount);

    Promotion findNewestPromotion();

    List<Promotion> findAllValidPromotions(LocalDate from);
}
