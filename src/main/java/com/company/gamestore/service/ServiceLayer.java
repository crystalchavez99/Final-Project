package com.company.gamestore.service;

import com.company.gamestore.model.*;
import com.company.gamestore.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Locale;
import java.util.Optional;

@Component
public class ServiceLayer {
    private ConsoleRepository consoleRepository;
    private GameRepository gameRepository;
    private InvoiceRepository invoiceRepository;
    private TShirtRepository tShirtRepository;
    private FeeRepository feeRepository;
    private TaxRepository taxRepository;

    @Autowired
    public ServiceLayer(
            ConsoleRepository consoleRepository,
            GameRepository gameRepository,
            InvoiceRepository invoiceRepository,
            TShirtRepository tShirtRepository,
            TaxRepository taxRepository,
            FeeRepository feeRepository
    ){
    this.consoleRepository = consoleRepository;
    this.gameRepository = gameRepository;
    this.invoiceRepository = invoiceRepository;
    this.tShirtRepository = tShirtRepository;
    this.feeRepository = feeRepository;
    this.taxRepository = taxRepository;
    }

    @Transactional
    public Invoice saveInvoice(Invoice invoice) {
        int itemId = invoice.getItemId();
        Boolean isFound = false;
        BigDecimal price = new BigDecimal(0);
        int quantity = 0;
        Console consoleItem = null;
        Game gameItem = null;
        TShirt tshirtItem = null;


        String itemType = invoice.getItemType().toLowerCase(Locale.ROOT);
        Boolean validType = itemType.equals("game") || itemType.equals("console") || itemType.equals("tshirt");
        if (validType == false) {
            throw new IllegalArgumentException("Invalid item type");
        }
        if(itemType.equals("console")){
            Optional<Console> console = consoleRepository.findById(itemId);
            isFound = console.isPresent();
            price = console.get().getPrice();
            quantity = console.get().getQuantity();
            consoleItem = console.get();
        }else if(itemType.equals("game")){
            Optional<Game> game = gameRepository.findById(itemId);
            isFound = game.isPresent();
            price = game.get().getPrice();
            quantity = game.get().getQuantity();
            gameItem = game.get();
        }else if(itemType.equals("tshirt")) {
            Optional<TShirt> tshirt = tShirtRepository.findById(itemId);
            isFound = tshirt.isPresent();
            price = tshirt.get().getPrice();
            quantity = tshirt.get().getQuantity();
            tshirtItem = tshirt.get();
        }

        if(isFound == false){
            throw new IllegalArgumentException("Item does not exist");
        }

        invoice.setUnitPrice(price);

        if(quantity <=0 || quantity < invoice.getQuantity()){
            throw new IllegalArgumentException("Quantity is not enough");
        }

        Optional<Fee> fee = feeRepository.findById(invoice.getItemType());

        BigDecimal invoiceFee = fee.get().getFee();
        System.out.println(itemType + " " + fee + invoiceFee + "YAYAYAYA");
        if(invoice.getQuantity() > 10){
            invoice.setProcessingFee(invoiceFee.add(new BigDecimal(15.49)));
        }else{
            invoice.setProcessingFee(invoiceFee);
        }

        invoice.setItemId(itemId);

        BigDecimal subtotal = price.multiply(new BigDecimal(invoice.getQuantity()));
        invoice.setSubtotal(subtotal);

        String state = invoice.getState();

        Optional<Tax> stateTax = taxRepository.findById(state);
        if(stateTax.isPresent() == false){
            throw new IllegalArgumentException("Invalid State");
        }
        invoice.setTax(invoice.getSubtotal().multiply(stateTax.get().getRate()));

        BigDecimal total = subtotal.add(invoice.getProcessingFee().add(invoice.getTax()));
        invoice.setTotal(total.setScale(2, RoundingMode.HALF_EVEN));

        invoiceRepository.save(invoice);

        if(itemType.equals("console")){
            consoleItem.setQuantity(consoleItem.getQuantity() - invoice.getQuantity());
            consoleRepository.save(consoleItem);
        }else  if(itemType.equals("game")) {
            gameItem.setQuantity(gameItem.getQuantity() - invoice.getQuantity());
            gameRepository.save(gameItem);
        }else  if(itemType.equals("tshirt")) {
            tshirtItem.setQuantity(tshirtItem.getQuantity() - invoice.getQuantity());
            tShirtRepository.save(tshirtItem);
        }

        return invoice;
    }

}
