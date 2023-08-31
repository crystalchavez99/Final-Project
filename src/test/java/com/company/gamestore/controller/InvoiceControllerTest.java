package com.company.gamestore.controller;

import com.company.gamestore.model.*;
import com.company.gamestore.repository.*;
import com.company.gamestore.service.ServiceLayer;
import com.company.gamestore.viewmodel.InvoiceViewModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Optional;

import static org.mockito.Mockito.when;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@RunWith(SpringRunner.class)
@WebMvcTest(InvoiceController.class)
public class InvoiceControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ServiceLayer serviceLayer;

    @MockBean
    private TaxRepository taxRepository;

    @MockBean
    private FeeRepository feeRepository;

    @MockBean
    private TShirtRepository tShirtRepository;
    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void createInvoice() throws Exception{
        Fee fee = new Fee();
        Tax tax = new Tax();
        TShirt tshirt = new TShirt();

        fee.setProductType("tshirt");
        fee.setFee(BigDecimal.valueOf(.99));
        when(feeRepository.findById(fee.getProductType())).thenReturn(Optional.of(fee));

        tax.setState("CA");
        tax.setRate(BigDecimal.valueOf(.06));
        when(taxRepository.findById(tax.getState())).thenReturn(Optional.of(tax));

        tshirt.setColor("red");
        tshirt.setDescription("coca-cola shirt");

        tshirt.setPrice(BigDecimal.valueOf(12L, 2));

        tshirt.setSize("medium");
        tshirt.setQuantity(100);
        when(tShirtRepository.findById(tshirt.getId())).thenReturn(Optional.of(tshirt));

        InvoiceViewModel invoiceViewModel = new InvoiceViewModel();

        invoiceViewModel.setName("Shawn Haven");
        invoiceViewModel.setStreet("420 Happy Ave");
        invoiceViewModel.setCity("Lakewood");
        invoiceViewModel.setState(tax.getState());
        invoiceViewModel.setZipcode("91387");
        invoiceViewModel.setItemType("tshirt");
        invoiceViewModel.setItemId(tshirt.getId());
        invoiceViewModel.setQuantity(10);

        String inputJson = mapper.writeValueAsString(invoiceViewModel);

        mockMvc.perform(
                post("/invoices").
                        content(inputJson).contentType(MediaType.APPLICATION_JSON)
        ).andDo(print())
                .andExpect(status().isCreated());
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
    void shouldGetInvoiceById() throws Exception {
        mockMvc.perform(get("/invoices/3"))
                .andDo(print())
                .andExpect(status().isOk());
    }
    //read by name
    @Test
    void shouldGetInvoicesByCustomerName() throws Exception {
        mockMvc.perform(get("/invoices/name/Shawn+Haven"))
                .andDo(print())
                .andExpect(status().isOk());
    }


}
