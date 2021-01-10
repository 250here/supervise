package com.supervise.tasksystem.dao;

import com.supervise.tasksystem.model.Market;
import com.supervise.tasksystem.model.MarketTask;
import com.supervise.tasksystem.model.MarketTaskGroup;
import com.supervise.tasksystem.model.MarketTaskItem;
import com.supervise.tasksystem.service.MarketTaskGroupService;
import com.supervise.tasksystem.service.MarketTaskItemService;
import com.supervise.tasksystem.util.VirtualTime;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class MarketTaskGroupTest {
    @Autowired
    MarketTaskGroupDao marketTaskGroupDao;
    @Autowired
    MarketDao marketDao;
    @Autowired
    MarketTaskItemDao marketTaskItemDao;
    @Autowired
    MarketTaskGroupService marketTaskGroupService;
    void setUp() {
    }

    @Test
    void testGetUnfinishedMarketTasks(){
        MarketTaskGroup marketTaskGroup = marketTaskGroupDao.findById(1).get();
        List<MarketTask> marketTaskList = marketTaskGroupService.getMarketTasks(marketTaskGroup.getMarketTaskGroupId());

        assertEquals(1,marketTaskList.size());
    }

    @Test
    void testMakeMarketTaskGroup() {

        marketTaskGroupService.makeMarketTaskGroup(VirtualTime.getDate());

        List<MarketTaskGroup> marketTaskGroupsList = marketTaskGroupDao.findAll();
        assertEquals(2,marketTaskGroupsList.size());
    }

    @Test
    void testAddMarketTask() {
        MarketTaskGroup marketTaskGroup = marketTaskGroupDao.findById(1).get();
        Market market = marketDao.findById(1).get();
        List<MarketTaskItem> marketTaskItemList = new ArrayList<>();
        marketTaskItemList.add(marketTaskItemDao.findById(1).get());
        marketTaskGroupService.addMarketTask(marketTaskGroup,market,marketTaskItemList);

        assertEquals(2,marketTaskGroup.getMarketTasks().size());
    }

    @Test
    void testGetMarketTasks() {
        MarketTaskGroup marketTaskGroup = marketTaskGroupDao.findById(1).get();
        List<MarketTask> marketTaskList = marketTaskGroupService.getMarketTasks(marketTaskGroup.getMarketTaskGroupId());

        assertEquals(2,marketTaskList.size());
    }

    @AfterEach
    void tearDown() {
    }
}
