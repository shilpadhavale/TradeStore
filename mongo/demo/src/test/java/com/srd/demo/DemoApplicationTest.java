package com.srd.demo;

import static org.mockito.Mockito.*;

import com.srd.demo.entity.Trade;
import com.srd.demo.entity.TradeMongo;
import com.srd.demo.repo.jpa.TradeSqlRepo;
import com.srd.demo.repo.mongo.TradeMongoRepo;
import com.srd.demo.service.TradeMongoService;
import com.srd.demo.service.TradeSqlService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.CommandLineRunner;

public class DemoApplicationTest {

    @Mock
    private TradeMongoRepo tradeMongoRepo;

    @Mock
    private TradeSqlRepo tradeSqlRepo;

    private CommandLineRunner commandLineRunner;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        commandLineRunner = new DemoApplication().commandLineRunner(tradeMongoRepo, tradeSqlRepo);
    }

    @Test
    void testCommandLineRunner() throws Exception {
        // Arrange
        Trade trade = new Trade(4, "Shishir", 4, "Vyas");

        // Act
        commandLineRunner.run();

        // Assert
        verify(tradeMongoRepo, times(0)).save(any(TradeMongo.class)); // MongoDB service is not called in this test
        verify(tradeSqlRepo, times(1)).save(any(Trade.class)); // SQL service is called once
    }
}
