package com.company.gamestore.controller;

import com.company.gamestore.model.Game;
import com.company.gamestore.model.Invoice;
import com.company.gamestore.repository.GameRepository;
import com.company.gamestore.repository.InvoiceRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.math.MathContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(InvoiceController.class)
public class InvoiceControllerTest {
    @MockBean
    private InvoiceRepository invoiceRepository;
    private GameRepository gameRepository;

    @Autowired
    private MockMvc mockMvc;

    private Invoice invoice;
    private Game game;

    ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void setUp() throws Exception{
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

    //read all
    @Test
    void shouldGetAllInvoices() throws Exception {
        mockMvc.perform(get("/invoices"))
                .andDo(print())
                .andExpect(status().isOk());
    }
    //read by id
    @Test
    void shouldGetGameById() throws Exception {
        mockMvc.perform(get("/invoices/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }
    //read by name
    @Test
    void shouldGetGameByTitle() throws Exception {
        mockMvc.perform(get("/invoices/name/John+Doe"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    //create
    @Test
    void shouldCreateInvoice() throws Exception {
        String input = mapper.writeValueAsString(game);

        mockMvc.perform(post("/invoices")
                        .content(input)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }
}
