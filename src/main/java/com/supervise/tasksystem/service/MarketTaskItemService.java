package com.supervise.tasksystem.service;

import com.supervise.tasksystem.dao.MarketTaskDao;
import com.supervise.tasksystem.dao.MarketTaskItemDao;
import com.supervise.tasksystem.model.ExpertTaskItem;
import com.supervise.tasksystem.model.MarketTask;
import com.supervise.tasksystem.model.MarketTaskItem;
import com.supervise.tasksystem.model.ProductType;
import com.supervise.tasksystem.util.VirtualTime;
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

    public MarketTaskItem completeMarketTaskItem(int marketId, int marketTaskItemId, int unqualifiedNumber){        //完成检测项

        Date date = VirtualTime.getDate();
        Optional<MarketTaskItem> marketTaskItemOptional = marketTaskItemDao.findById(marketTaskItemId);
        MarketTaskItem marketTaskItem = marketTaskItemOptional.isPresent()?marketTaskItemOptional.get() : null;

        if(marketTaskItem == null){
            return marketTaskItem;
        }
        if(marketTaskItem.getMarketTask().getMarket().getMarketId() != marketId){
            return null;
        }
        marketTaskItem.setFinished(true);
        marketTaskItem.setFinishDate(date);
        marketTaskItem.setUnqualifiedNumber(unqualifiedNumber);
        marketTaskItemDao.save(marketTaskItem);

        MarketTask marketTask = marketTaskItem.getMarketTask();
        if(marketTaskService.hasUnfinishedItem(marketTask.getMarketTaskId())==false){
            marketTask.setFinished(true);
            marketTaskDao.save(marketTask);
    }
        return marketTaskItem;
    }
}
