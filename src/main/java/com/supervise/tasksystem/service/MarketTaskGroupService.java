package com.supervise.tasksystem.service;

import com.supervise.tasksystem.dao.MarketDao;
import com.supervise.tasksystem.dao.MarketTaskDao;
import com.supervise.tasksystem.dao.MarketTaskGroupDao;
import com.supervise.tasksystem.model.Market;
import com.supervise.tasksystem.model.MarketTask;
import com.supervise.tasksystem.model.MarketTaskGroup;
import com.supervise.tasksystem.model.MarketTaskItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MarketTaskGroupService {
    @Autowired
    MarketTaskGroupDao marketTaskGroupDao;
    @Autowired
    MarketTaskDao marketTaskDao;
    @Autowired
    MarketDao marketDao;

    public List<MarketTask> getUnfinishedMarketTasks(int marketTaskGroupId){       //查找一组监管任务下未完成的任务
        Optional<MarketTaskGroup> optionalMarketTaskGroup = marketTaskGroupDao.findById(marketTaskGroupId);
        MarketTaskGroup marketTaskGroup = optionalMarketTaskGroup.isPresent()?optionalMarketTaskGroup.get() : null;
        if(marketTaskGroup == null){
            System.out.println("查询数据为空");
            return null;
        }
        return marketTaskDao.findByMarketTaskGroupAndIsFinishedFalse(marketTaskGroup);
    }
    public MarketTaskGroup makeMarketTaskGroup(Date deadline){          //新建任务组
        MarketTaskGroup marketTaskGroup = new MarketTaskGroup();
        marketTaskGroup.setDeadline(deadline);
        marketTaskGroup.setMarketTasks(new ArrayList<MarketTask>());
        marketTaskGroupDao.save(marketTaskGroup);

        return marketTaskGroup;
    }

    public MarketTask addMarketTask(int marketTaskGroupId, int marketId, List<MarketTaskItem> itemList){
        MarketTaskGroup marketTaskGroup = marketTaskGroupDao.findById(marketTaskGroupId).get();
        Market market = marketDao.findById(marketId).get();
        MarketTask marketTask = new MarketTask();

        marketTask.setFinished(false);
        marketTask.setMarketTaskGroup(marketTaskGroup);
        marketTask.setMarket(market);
        marketTask.setMarketTaskItems(itemList);

        marketTaskDao.save(marketTask);
        return marketTask;
    }

    public MarketTask addMarketTask(MarketTaskGroup marketTaskGroup, Market market, List<MarketTaskItem> itemList){    //添加监管任务
        MarketTask marketTask = new MarketTask();
        marketTask.setFinished(false);
        marketTask.setMarketTaskGroup(marketTaskGroup);
        marketTask.setMarket(market);
        marketTask.setMarketTaskItems(itemList);

        marketTaskDao.save(marketTask);

        return marketTask;
    }

    public List<MarketTask> getMarketTasks(int marketTaskGroupId){          //获得任务组下的所有任务
        MarketTaskGroup marketTaskGroup = marketTaskGroupDao.findById(marketTaskGroupId).get();
        return marketTaskGroup.getMarketTasks();
    }

    public List<MarketTaskGroup> getAllMarketTaskGroup(){          //获得所有市场任务组
        List<MarketTaskGroup> marketTaskGroups = marketTaskGroupDao.findAll();

        return marketTaskGroups;
    }

    public int getUnqualifiedNumberInGroup(int marketTaskGroupId){          //得到市场任务组下所有不合格数
        MarketTaskGroup marketTaskGroup = marketTaskGroupDao.findById(marketTaskGroupId).get();
        List<MarketTask> marketTaskList = marketTaskGroup.getMarketTasks();
        int num = 0;
        for (MarketTask marketTask : marketTaskList){
            for (MarketTaskItem marketTaskItem : marketTask.getMarketTaskItems()){
                num += marketTaskItem.getUnqualifiedNumber();
            }
        }
        return num;
    }
}
