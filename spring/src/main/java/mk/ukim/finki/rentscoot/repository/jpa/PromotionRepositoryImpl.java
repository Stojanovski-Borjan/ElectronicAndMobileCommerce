package mk.ukim.finki.rentscoot.repository.jpa;

import mk.ukim.finki.rentscoot.model.Promotion;
import mk.ukim.finki.rentscoot.repository.PromotionRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public class PromotionRepositoryImpl implements PromotionRepository {

    private final JpaPromotionRepository repository;

    public PromotionRepositoryImpl(JpaPromotionRepository repository) {
        this.repository = repository;
    }

    @Override
    public Promotion createPromotion(Promotion promotion) {
        return this.repository.save(promotion);
    }

    @Override
    public List<Promotion> getAllPromotions() {
        return this.repository.findAll();
    }

    @Override
    public Optional<Promotion> findById(String name) {
        return this.repository.findById(name);
    }

    @Override
    public void deletePromotion(Promotion promotion) {
        this.repository.delete(promotion);
    }

    @Override
    public List<Promotion> findBestDiscountPromotions(double discount) {
        return this.repository.findByDiscountGreaterThanOrderByDiscountDesc(discount);
    }

    @Override
    public List<Promotion> findAllValidPromotions(LocalDate from) {
        return this.repository.findAllByValidFromBeforeOrderByDiscountDesc(from);
    }

    @Override
    public boolean exists(String name) {
        return this.repository.existsById(name);
    }
}
