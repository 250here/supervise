package com.supervise.tasksystem.dao;

import com.supervise.tasksystem.model.MarketTask;
import com.supervise.tasksystem.model.MarketTaskGroup;
import com.supervise.tasksystem.model.MarketTaskItem;
import com.supervise.tasksystem.service.MarketTaskItemService;
import com.supervise.tasksystem.service.MarketTaskService;
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

class MarketTaskDaoTest {
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
        List<MarketTask> marketTasks= marketTaskService.getUnfinishedMarketTasks(1);
        System.out.println(marketTasks);
//        Hibernate.initialize(marketTaskItem);
//        assertEquals(1,marketTaskItem.getMarketTaskItemId());


    }




    @AfterEach
    void tearDown() {
    }
}
