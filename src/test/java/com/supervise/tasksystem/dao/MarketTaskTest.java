package com.supervise.tasksystem.dao;

import com.supervise.tasksystem.model.MarketTask;
import com.supervise.tasksystem.model.MarketTaskGroup;
import com.supervise.tasksystem.model.MarketTaskItem;
import com.supervise.tasksystem.model.ProductType;
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

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest

class MarketTaskTest {
    @Autowired
    MarketTaskDao marketTaskDao;
    @Autowired
    ProductTypeDao productTypeDao;
    @Autowired
    MarketTaskService marketTaskService;
    @Autowired
    MarketTaskService marketTaskGroupService;
    @BeforeEach
    void setUp() {
    }

    @Test
    void testAddMarketTaskItem(){
        List<MarketTaskItem> marketTaskItemList = marketTaskService.getUnfinishedMarketTaskItems(1);
        MarketTask marketTask = marketTaskDao.findById(1).get();
        ProductType productType = productTypeDao.findById(1).get();
        marketTaskService.addMarketTaskItem(marketTask,productType);

        assertEquals(2,marketTask.getMarketTaskItems().size());

    }
    @Test
    void testGetUnfinishedMarketTaskItems(){
        MarketTask marketTask = marketTaskDao.findById(1).get();
        List<MarketTaskItem> marketTaskItemList = marketTaskService.getUnfinishedMarketTaskItems(marketTask.getMarketTaskId());

        assertEquals(1,marketTaskItemList.size());
    }

    @Test
    void testGrade(){
        MarketTask marketTask = marketTaskDao.findById(1).get();
        VirtualTime time = new VirtualTime("2021-01-10 00:00:00");
        int grade = marketTaskService.grade(marketTask.getMarketTaskId(),time);



        assertEquals(0,grade);
    }


    @AfterEach
    void tearDown() {
    }
}
