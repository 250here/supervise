package com.supervise.tasksystem.service;

import com.supervise.tasksystem.dao.MarketTaskDao;
import com.supervise.tasksystem.dao.MarketTaskItemDao;
import com.supervise.tasksystem.model.MarketTask;
import com.supervise.tasksystem.model.MarketTaskItem;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MarketTaskService {
    @Autowired
    MarketTaskDao marketTaskDao;
    @Autowired
    MarketTaskItemDao marketTaskItemDao;
    public List<MarketTaskItem> getUnfinishedMarketTaskItems(int marketTaskId){
        MarketTask marketTask = marketTaskDao.findById(marketTaskId).get();
        return marketTaskItemDao.findByMarketTaskAndIsFinishedFalse(marketTask);
    }
}
