package com.company.gamestore.service;


import com.company.gamestore.model.Fee;
import com.company.gamestore.model.Invoice;
import com.company.gamestore.model.TShirt;
import com.company.gamestore.model.Tax;
import com.company.gamestore.repository.*;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceLayerTest {

    @Autowired
    ServiceLayer serviceLayer;

    @Autowired
    TaxRepository taxRepository;

    @Autowired
    FeeRepository feeRepository;

    @Autowired
    TShirtRepository tShirtRepository;

    @Autowired
    InvoiceRepository invoiceRepository;

    @Autowired
    GameRepository gameRepository;

    @BeforeEach
    public void setUp(){
        serviceLayer.deleteAll();
        taxRepository.deleteAll();
        feeRepository.deleteAll();
        tShirtRepository.deleteAll();
    }

    public Fee createFee() {
        Fee fee = new Fee();
        fee.setProductType("tshirt");
        fee.setFee(BigDecimal.valueOf(.99));
        return fee;
    }

    public Tax createTax() {
        Tax tax = new Tax();
        tax.setState("OR");
        tax.setRate(BigDecimal.valueOf(.07));
        return tax;
    }

    public TShirt createTShirt() {
        TShirt tshirt = new TShirt();
        tshirt.setColor("red");
        tshirt.setDescription("coca-cola shirt");

        tshirt.setPrice(BigDecimal.valueOf(12L, 2));

        tshirt.setSize("medium");
        tshirt.setQuantity(100);
        return tshirt;
    }

    public Invoice createInvoice(Tax tax, TShirt tshirt) {
        Invoice invoice = new Invoice();
        invoice.setName("Shawn Haven");
        invoice.setStreet("420 Happy Ave");
        invoice.setCity("Lakewood");
        invoice.setState(tax.getState());
        invoice.setZipcode("91387");
        invoice.setItemType("tshirt");
        invoice.setItemId(tshirt.getId());
        invoice.setQuantity(10);
        return invoice;
    }

    @Test
    public void save() throws Exception {
        Fee fee = createFee();
        //System.out.println(fee + "LES GO" + feeRepository);
        feeRepository.save(fee);

        Tax tax = createTax();
        taxRepository.save(tax);

        TShirt tshirt = createTShirt();
        tShirtRepository.save(tshirt);

        Invoice invoice = createInvoice(tax, tshirt);
        Invoice savedInvoice = serviceLayer.saveInvoice(invoice);
        savedInvoice.setTax(savedInvoice.getTax().setScale(2, RoundingMode.HALF_EVEN));


        Optional<Invoice> invoiceList = invoiceRepository.findById(savedInvoice.getId());
        assertEquals(invoiceList.get(), savedInvoice);
    }

    @Test
    public void findAll() throws Exception {
        Fee fee = createFee();
        feeRepository.save(fee);

        Tax tax = createTax();
        taxRepository.save(tax);

        TShirt tshirt = createTShirt();
        tShirtRepository.save(tshirt);

        Invoice invoice = createInvoice(tax, tshirt);
        Invoice savedInvoice = serviceLayer.saveInvoice(invoice);
        savedInvoice.setTax(savedInvoice.getTax().setScale(2, RoundingMode.HALF_EVEN));

        Optional<Invoice> invoiceList = invoiceRepository.findById(savedInvoice.getId());
        assertEquals(invoiceList.get(), savedInvoice);
    }
}
