package com.company.gamestore.repository;

import com.company.gamestore.model.Game;
import com.company.gamestore.model.Invoice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class InvoiceRepositoryTest {
    @Autowired
    private InvoiceRepository invoiceRepository;
    private GameRepository gameRepository;

    private Invoice invoice;
    private Game game;

    @BeforeEach
    public void setUp() throws Exception{
        invoiceRepository.deleteAll();
        gameRepository.deleteAll();;

        game = new Game();
        BigDecimal gameDecimal = new BigDecimal(24.99);
        MathContext mc = new MathContext(4);
        game.setTitle("Super Monkey Ball");
        game.setEsrbRating("Everyone");
        game.setDescription("Call your friends and warn your neighbors, it's time to have a ball! Go bananas with 90+ stages, multi-player madness, and 7 cool ways to play! Equal parts \"party\" and \"game\", Super Monkey Ball could be the most \"well-rounded\" game you've ever played!");
        game.setPrice(gameDecimal.round(mc));
        game.setStudio("Amusement Vision");
        game.setQuantity(5);
        game = gameRepository.save(game);

        invoice = new Invoice();
        invoice.setName("John Doe");
        invoice.setStreet("1234 Street Name");
        invoice.setCity("City Name");
        invoice.setState("California");
        invoice.setZipcode("00000");
        invoice.setItemType("game");
        invoice.setItemId(game.getId());
        invoice.setUnitPrice(game.getPrice());
        invoice.setQuantity(5);
        //invoice.setSubtotal();
        //invoice.setTax();
        //invoice.setProcessingFee();
        //invoice.setTotal();
        invoice = invoiceRepository.save(invoice);
    }
    //create
    @Test
    void shouldCreateInvoice(){
        Optional<Invoice> invoice2 = invoiceRepository.findById(invoice.getId());
        assertEquals(invoice2.get(), invoice);
    }
    //read by id
    @Test
    void shouldFindInvoiceById() {
        Optional<Invoice> invoice2 = invoiceRepository.findById(invoice.getId());
        assertEquals(invoice2.get(), invoice);
    }

    //read all
    @Test
    void shouldFindAllInvoices(){
        List<Invoice> invoices = invoiceRepository.findAll();
        assertEquals(1,invoices.size());
    }

    //by customer name
    @Test
    void shouldFindInvoiceByName() {
        List<Invoice> invoice2 = invoiceRepository.findByName(invoice.getName());
        assertEquals(1, invoice2.size());
    }
}
