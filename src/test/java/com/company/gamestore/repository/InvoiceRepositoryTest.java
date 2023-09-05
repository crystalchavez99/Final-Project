package com.company.gamestore.repository;

import com.company.gamestore.model.*;
import com.company.gamestore.service.ServiceLayer;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@RunWith(SpringRunner.class)
@SpringBootTest
public class InvoiceRepositoryTest {
    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    TaxRepository taxRepository;

    @Autowired
    FeeRepository feeRepository;

    @Autowired
    TShirtRepository tShirtRepository;

    @Autowired
    ServiceLayer serviceLayer;

    private Fee fee;

    private Tax tax;

    private TShirt tShirt;

    private Invoice invoice;

    @BeforeEach
    public void setUp() throws Exception {
        invoiceRepository.deleteAll();
        taxRepository.deleteAll();
        feeRepository.deleteAll();
        tShirtRepository.deleteAll();
        serviceLayer.deleteAll();

        fee = new Fee();
        fee.setProductType("tshirt");
        fee.setFee(BigDecimal.valueOf(.99));

        feeRepository.save(fee);

        tax = new Tax();
        tax.setState("OR");
        tax.setRate(BigDecimal.valueOf(.07));

        taxRepository.save(tax);

        tShirt = new TShirt();
        tShirt.setColor("red");
        tShirt.setDescription("coca-cola shirt");
        tShirt.setPrice(BigDecimal.valueOf(12L, 2));
        tShirt.setSize("medium");
        tShirt.setQuantity(100);

        tShirtRepository.save(tShirt);

        invoice = new Invoice();
        invoice.setName("Shawn Haven");
        invoice.setStreet("420 Happy Ave");
        invoice.setCity("Lakewood");
        invoice.setState(tax.getState());
        invoice.setZipcode("91387");
        invoice.setItemType("tshirt");
        invoice.setItemId(tShirt.getId());
        invoice.setQuantity(10);

        invoice = serviceLayer.saveInvoice(invoice);
        invoice.setTax(invoice.getTax().setScale(2, RoundingMode.HALF_EVEN));
    }

    //create
    @Test
    void shouldCreateInvoice(){
        Optional<Invoice> invoiceREPO = invoiceRepository.findById(invoice.getId());
        assertEquals(invoiceREPO.get(), invoice);
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
        System.out.println(invoices.size() + "XXXXXXXXXx");
        assertEquals(1,invoices.size());
    }

    //by customer name
    @Test
    void shouldFindInvoiceByName() {
        List<Invoice> invoice2 = invoiceRepository.findByName(invoice.getName());
        assertEquals(1, invoice2.size());
    }
}
