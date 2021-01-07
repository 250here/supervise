package com.supervise.tasksystem.dao;

import com.supervise.tasksystem.model.Expert;
import com.supervise.tasksystem.model.Market;
import com.supervise.tasksystem.model.MarketTask;
import com.supervise.tasksystem.model.MarketTaskItem;
import com.supervise.tasksystem.service.MarketTaskService;
import org.hibernate.Hibernate;
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
    @Autowired
    MarketTaskItemDao marketTaskItemDao;
    @Autowired
    MarketTaskService marketTaskService;
    @BeforeEach
    void setUp() {
    }

    @Test
    void testFindById(){
        MarketTaskItem marketTaskItem = marketTaskService.getUnfinishedMarketTaskItems(1).get(0);
        Hibernate.initialize(marketTaskItem);
        assertEquals(1,marketTaskItem.getMarketTaskItemId());


    }




    @AfterEach
    void tearDown() {
    }
}
