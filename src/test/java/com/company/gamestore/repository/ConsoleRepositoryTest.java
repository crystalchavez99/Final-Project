package com.company.gamestore.repository;

import com.company.gamestore.model.Console;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

//@RunWith(SpringRunner.class)
@SpringBootTest
public class ConsoleRepositoryTest {
    @Autowired
    ConsoleRepository consoleRepository;

    Console console;
    @BeforeEach
    public void setUp() throws Exception {
        consoleRepository.deleteAll();

        console = new Console();
        console.setPrice(BigDecimal.valueOf(299.99));
        console.setModel("Nintendo Switch");
        console.setManufacturer("Nintendo");
        console.setProcessor("ARM 4 Cortex-A57");
        console.setQuantity(1);
        console.setId(3);

        console = consoleRepository.save(console);
    }

    @Test
    public void shouldCreateConsole(){
        List<Console> console1 = consoleRepository.findByManufacturer(console.getManufacturer());
        assertEquals(console1.get(0),console);
    }

    @Test
    public void shouldGetConsoleById(){
        Optional<Console> console1 = consoleRepository.findById(console.getConsoleId());
        assertEquals(console1.get(),console);
    }

    @Test
    public void testGetAllConsoles(){
        Console console2 = new Console();
        console2.setPrice(BigDecimal.valueOf(499.99));
        console2.setModel("PS5");
        console2.setManufacturer("Sony");
        console2.setProcessor("AMD Zen 2");
        console2.setQuantity(5);
        console2.setId(4);

        console2 = consoleRepository.save(console2);
        List<Console> consoleCheck = consoleRepository.findAll();
        assertEquals(consoleCheck.get(1),console2);
    }

    @Test
    public void shouldDeleteConsole(){
        consoleRepository.deleteById(console.getConsoleId());
        Optional<Console> console1 = consoleRepository.findById(console.getConsoleId());
        assertFalse(console1.isPresent());
    }

    @Test
    public void shouldUpdateConsole(){
        Console console2 = new Console();
        console2.setPrice(BigDecimal.valueOf(499.99));
        console2.setModel("PS5");
        console2.setManufacturer("Sony");
        console2.setProcessor("AMD Zen 2");
        console2.setQuantity(5);
        console2.setId(4);

        console2.setModel("PS4");
        console2 = consoleRepository.save(console2);

        Optional<Console> consoleOpt = consoleRepository.findById(console2.getConsoleId());
        assertEquals(consoleOpt.get(),console2);
    }

    @Test
    public void shouldGetConsolesByManufacturer(){
        List<Console> consoleList = consoleRepository.findByManufacturer(console.getManufacturer());
        assertEquals(consoleList.get(0),console);
    }
}
