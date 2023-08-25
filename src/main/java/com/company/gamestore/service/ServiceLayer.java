package com.company.gamestore.service;

import com.company.gamestore.model.Console;
import com.company.gamestore.model.Invoice;
import com.company.gamestore.repository.ConsoleRepository;
import com.company.gamestore.repository.GameRepository;
import com.company.gamestore.repository.InvoiceRepository;
import com.company.gamestore.repository.TShirtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;
import java.util.Optional;

@Component
public class ServiceLayer {
    private ConsoleRepository consoleRepository;
    private GameRepository gameRepository;
    private InvoiceRepository invoiceRepository;
    private TShirtRepository tShirtRepository;

    @Autowired
    public ServiceLayer(
            ConsoleRepository consoleRepository,
            GameRepository gameRepository,
            InvoiceRepository invoiceRepository,
            TShirtRepository tShirtRepository
    ){
    this.consoleRepository = consoleRepository;
    this.gameRepository = gameRepository;
    this.invoiceRepository = invoiceRepository;
    this.tShirtRepository = tShirtRepository;
    }

    @Transactional
    public Invoice saveInvoice(Invoice invoice) {
        int itemId = invoice.getItemId();
        String itemType = invoice.getItemType().toLowerCase(Locale.ROOT);
        Boolean validType = itemType.equals("game") || itemType.equals("console") || itemType.equals("tshirt");
        if (validType == false) {
            throw new IllegalArgumentException("Invalid item type");
        }
        if(itemType.equals("console")){
            Optional<Console> console = consoleRepository.findById(itemId);
        }
    }

}
