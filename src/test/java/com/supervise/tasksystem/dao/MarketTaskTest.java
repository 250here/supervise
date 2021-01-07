package com.supervise.tasksystem.dao;

import com.supervise.tasksystem.model.Expert;
import com.supervise.tasksystem.model.Market;
import com.supervise.tasksystem.model.MarketTask;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest

class MarketTaskTest {
    @Autowired
    MarketTaskDao marketTaskDao;
    @BeforeEach
    void setUp() {
    }

    @Test
    void testFindById(){
        MarketTask marketTask = marketTaskDao.findAll().get(0);
        assertEquals(1,marketTask.getMarketTaskId());


    }




    @AfterEach
    void tearDown() {
    }
}