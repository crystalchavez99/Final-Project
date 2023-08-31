package com.company.gamestore.controller;

import com.company.gamestore.model.Fee;
import com.company.gamestore.model.Invoice;
import com.company.gamestore.model.TShirt;
import com.company.gamestore.model.Tax;
import com.company.gamestore.repository.FeeRepository;
import com.company.gamestore.repository.TShirtRepository;
import com.company.gamestore.repository.TaxRepository;
import com.company.gamestore.service.ServiceLayer;
import com.company.gamestore.viewmodel.InvoiceViewModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@WebMvcTest(InvoiceController.class)
class InvoiceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ServiceLayer invoiceServiceLayer;

    @MockBean
    private TaxRepository taxRepository;

    @MockBean
    private FeeRepository feeRepository;

    @MockBean
    private TShirtRepository tShirtRepository;
    private ObjectMapper mapper = new ObjectMapper();

    @Test
    void getAllInvoices() throws Exception {
        mockMvc.perform(get("/invoices"))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    void getInvoiceById() throws Exception {
        mockMvc.perform(get("/invoices/1"))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    void getInvoiceByName() throws Exception {
        mockMvc.perform(get("/invoices/names/John Doe"))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    void createInvoice() throws Exception {
        Tax tax = new Tax();
        Fee fee = new Fee();
        TShirt tshirt = new TShirt();

        fee.setProductType("tshirt");
        fee.setFee(BigDecimal.valueOf(.99));
        when(feeRepository.findById(fee.getProductType())).thenReturn(Optional.of(fee));

        tax.setState("OR");
        tax.setRate(BigDecimal.valueOf(.075));
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

        mockMvc.perform(post("/invoices").content(inputJson).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());

    }
}