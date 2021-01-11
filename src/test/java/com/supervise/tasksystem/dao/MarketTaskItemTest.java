package com.supervise.tasksystem.dao;

import com.supervise.tasksystem.model.MarketTask;
import com.supervise.tasksystem.model.MarketTaskItem;
import com.supervise.tasksystem.service.MarketTaskItemService;
import com.supervise.tasksystem.service.MarketTaskService;
import com.supervise.tasksystem.util.VirtualTime;
import org.hibernate.Hibernate;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest

public class MarketTaskItemTest {
    @Autowired
    MarketTaskItemDao marketTaskItemDao;
    @Autowired
    MarketTaskItemService marketTaskItemService;
    void setUp() {
    }

    @Test
    void testFindById(){
        MarketTaskItem marketTaskItem = marketTaskItemDao.findById(1).get();

        marketTaskItemService.completeMarketTaskItem(marketTaskItem.getMarketTaskItemId(),1,VirtualTime.getDate());

    }
    @Test
    void testCompleteMarketTaskItem(){

        MarketTaskItem marketTaskItem = marketTaskItemDao.findById(1).get();
        marketTaskItemService.completeMarketTaskItem(marketTaskItem.getMarketTaskItemId(),1,VirtualTime.getDate());

        assertEquals(true,marketTaskItem.isFinished());
        assertEquals(true,marketTaskItem.getMarketTask().isFinished());
    }



    @AfterEach
    void tearDown() {
    }
}
