package com.company.gamestore.repository;

import com.company.gamestore.model.TShirt;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class TShirtRepositoryTest {

    @Autowired
    private TShirtRepository tshirtRepository;

    private TShirt tshirt;

    @BeforeEach
    public void setUp() {
        tshirtRepository.deleteAll();

        tshirt = new TShirt();

        BigDecimal decimal = new BigDecimal("24.99");
        MathContext mc = new MathContext(4);

        tshirt.setSize("Medium");
        tshirt.setColor("Red");
        tshirt.setDescription("100% Cotton, Machine Wash Cold, Air Dry or Tumble Dry Low");
        tshirt.setPrice(decimal.round(mc));
        tshirt.setQuantity(20);

        tshirt = tshirtRepository.save(tshirt);
    }

    @Test
    void shouldCreateTShirt(){
        Optional<TShirt> tshirt2 = tshirtRepository.findById(tshirt.getId());
        assertEquals(tshirt2.get(), tshirt);
    }

    @Test
    void shouldFindAllTShirts(){
        List<TShirt> tshirts = tshirtRepository.findAll();
        assertEquals(1,tshirts.size());
    }

    @Test
    void shouldFindTShirtById() {
        Optional<TShirt> tshirt2 = tshirtRepository.findById(tshirt.getId());
        assertEquals(tshirt2.get(), tshirt);
    }

    @Test
    void shouldUpdateTShirt(){
        tshirt.setColor("Black");

        tshirtRepository.save(tshirt);

        Optional<TShirt> tshirt2 = tshirtRepository.findById(tshirt.getId());
        assertEquals(tshirt2.get(), tshirt);
    }

    @Test
    void shouldDeleteGameById(){
        tshirtRepository.deleteById(tshirt.getId());

        Optional<TShirt> tshirt2 = tshirtRepository.findById(tshirt.getId());
        assertFalse(tshirt2.isPresent());
    }

    @Test
    void shouldFindTShirtByColor() {
        List<TShirt> tshirt2 = tshirtRepository.findByColor(tshirt.getColor());
        assertEquals(1, tshirt2.size());
    }

    @Test
    void shouldFindTShirtBySize() {
        List<TShirt> tshirt2 = tshirtRepository.findBySize(tshirt.getSize());
        assertEquals(1, tshirt2.size());
    }
}
