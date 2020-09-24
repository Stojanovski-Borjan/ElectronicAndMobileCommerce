package mk.ukim.finki.rentscoot.service.implementation;

import mk.ukim.finki.rentscoot.model.Promotion;
import mk.ukim.finki.rentscoot.model.exceptions.InvalidPromotionException;
import mk.ukim.finki.rentscoot.repository.PromotionRepository;
import mk.ukim.finki.rentscoot.service.PromotionService;
import org.apache.logging.log4j.util.PropertySource;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PromotionServiceImplementation implements PromotionService {
    private final PromotionRepository promotionRepository;

    public PromotionServiceImplementation(PromotionRepository promotionRepository) {
        this.promotionRepository = promotionRepository;
    }

    @Override
    public Promotion createPromotion(String name, String description, double discount, LocalDate validFrom, LocalDate validTo) {
        Promotion promotion = new Promotion();
        promotion.setName(name);
        promotion.setDescription(description);
        promotion.setDiscount(discount);
        promotion.setValidFrom(validFrom);
        promotion.setValidTo(validTo);
        return this.promotionRepository.createPromotion(promotion);
    }

    @Override
    public List<Promotion> getAllPromotions() {
        return this.promotionRepository.getAllPromotions();
    }

    @Override
    public Promotion getPromotion(String name) {
        return this.promotionRepository.findById(name).orElseThrow(InvalidPromotionException::new);
    }

    @Override
    public Promotion updatePromotion(String name, String description, double discount, LocalDate validFrom, LocalDate validTo) {
        Promotion promotion = this.promotionRepository.findById(name).orElseThrow(()-> new InvalidPromotionException("Updating error"));
        promotion.setDescription(description);
        promotion.setDiscount(discount);
        promotion.setValidFrom(validFrom);
        promotion.setValidTo(validTo);
        return this.promotionRepository.createPromotion(promotion);
    }

    @Override
    public void deletePromotion(String name) {
        Promotion promotion = this.promotionRepository.findById(name).orElseThrow(()-> new InvalidPromotionException("Deletion error"));
        this.promotionRepository.deletePromotion(promotion);
    }

    @Override
    public List<Promotion> findBestDiscountPromotions(double discount) {
        return this.promotionRepository.findBestDiscountPromotions(discount);
    }

    @Override
    public Promotion findNewestPromotion() {
        List<Promotion> allValid = this.promotionRepository.findAllValidPromotions(LocalDate.now());
        // Natural order supposed to be oldest -> newest.So with reversing we get newest -> oldest. Also there is smarter solution with max
        if(allValid.size()>0) {
            return allValid.stream().max(Comparator.comparing(Promotion::getValidFrom)).orElseThrow(()->new InvalidPromotionException("Comparator error")); //change to exception
            //return allValid.stream().sorted(Comparator.comparing(Promotion::getValidFrom,Comparator.nullsLast(Comparator.naturalOrder())).reversed()).findFirst().orElseThrow(InvalidPromotionException::new);
        }
        else return null;//throw new InvalidPromotionException("No valid promotions found");
    }

    @Override
    public List<Promotion> findAllValidPromotions(LocalDate from) {
        return this.promotionRepository.findAllValidPromotions(from);
    }
}
