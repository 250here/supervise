package com.supervise.tasksystem.service;

import com.supervise.tasksystem.dao.MarketTaskDao;
import com.supervise.tasksystem.dao.MarketTaskItemDao;
import com.supervise.tasksystem.model.MarketTask;
import com.supervise.tasksystem.model.MarketTaskItem;
import com.supervise.tasksystem.model.ProductType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MarketTaskItemService {
    @Autowired
    MarketTaskDao marketTaskDao;
    @Autowired
    MarketTaskItemDao marketTaskItemDao;
    @Autowired
    MarketTaskService marketTaskService;

    public void completeMarketTaskItem(MarketTaskItem marketTaskItem, Date date){        //完成检测项
        marketTaskItem.setFinished(true);
        marketTaskItem.setFinishDate(date);
        marketTaskItemDao.save(marketTaskItem);

        MarketTask marketTask = marketTaskItem.getMarketTask();
        if(marketTaskService.hasUnfinishedItem(marketTask)==false){
            marketTask.setFinished(true);
            marketTaskDao.save(marketTask);
    }
    }
}
