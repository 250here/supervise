package com.supervise.tasksystem.service;

import com.supervise.tasksystem.dao.MarketTaskDao;
import com.supervise.tasksystem.dao.MarketTaskGroupDao;
import com.supervise.tasksystem.dao.MarketTaskItemDao;
import com.supervise.tasksystem.model.*;
import com.supervise.tasksystem.util.VirtualTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MarketTaskService {
    @Autowired
    MarketTaskDao marketTaskDao;
    @Autowired
    MarketTaskGroupDao marketTaskGroupDao;
    @Autowired
    MarketTaskItemDao marketTaskItemDao;


    public void addMarketTaskItem(MarketTask marketTask, ProductType productType){        //添加检测项
        MarketTaskItem marketTaskItem = new MarketTaskItem();
        marketTaskItem.setFinished(false);
        marketTaskItem.setMarketTask(marketTask);
        marketTaskItem.setProductType(productType);
        marketTaskItemDao.save(marketTaskItem);
    }

    public List<MarketTaskItem> getUnfinishedMarketTaskItems(int marketTaskId){                 //查找某市场任务下未完成的类别
        Optional<MarketTask> marketTaskOptional = marketTaskDao.findById(marketTaskId);
        MarketTask marketTask = marketTaskOptional.isPresent()?marketTaskOptional.get() : null;
        if(marketTask == null){
            System.out.println("查询数据为空");
            return null;
        }
        return marketTaskItemDao.findByMarketTaskAndIsFinishedFalse(marketTask);
    }

    public int grade(int marketTaskId,VirtualTime time){          //查看该市场得分情况
        MarketTask marketTask = marketTaskDao.findById(marketTaskId).get();
        Market market = marketTaskDao.findByMarketTask(marketTask);
        MarketTaskGroup marketTaskGroup = marketTaskGroupDao.findById(marketTask.getMarketTaskGroup().getMarketTaskGroupId()).get();
        List<MarketTaskItem> marketTaskItemList = marketTask.getMarketTaskItems();
        int grade = 0;
        if(hasUnfinishedItem(marketTask)==false && getLatestDate(marketTask).compareTo(marketTaskGroup.getDeadline())!=1){
            grade +=10;
//            System.out.println(market.getMarketName() +" 得分：" + grade);
        }

        if((hasUnfinishedItem(marketTask)==false &&getLatestDate(marketTask).getTime() - marketTaskGroup.getDeadline().getTime() <= 1728000000 && getLatestDate(marketTask).getTime() - marketTaskGroup.getDeadline().getTime()>0)
           || (hasUnfinishedItem(marketTask)==true && time.getDate().getTime() - marketTaskGroup.getDeadline().getTime() <= 1728000000 && time.getDate().getTime() - marketTaskGroup.getDeadline().getTime() > 0) ){
            grade -= 10;
        }
        if((hasUnfinishedItem(marketTask)==false &&getLatestDate(marketTask).getTime() - marketTaskGroup.getDeadline().getTime() > 1728000000)
            ||(hasUnfinishedItem(marketTask)==true && time.getDate().getTime() - marketTaskGroup.getDeadline().getTime() > 1728000000)){  //完成时间超过20天
            grade -= 20;
        }
        return grade;
    }

    public boolean hasUnfinishedItem(MarketTask marketTask){          //是否有未完成的类别
        List<MarketTaskItem> marketTaskItemList = marketTaskItemDao.findByMarketTask(marketTask);
        for (MarketTaskItem item : marketTaskItemList){
            if(item.isFinished() == false){
                return true;
            }
        }
        return false;
    }

    public Date getLatestDate(MarketTask marketTask){             //得到最后完成时间
        List<MarketTaskItem> marketTaskItemList = marketTask.getMarketTaskItems();
        String s = "1999-01-01 00:00:00";
        Date date;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            date = simpleDateFormat.parse(s);

            for(MarketTaskItem item : marketTaskItemList){
                if(item.getFinishDate()!=null && item.getFinishDate().compareTo(date)==1){
                    date = item.getFinishDate();
                }
            }
            return date;
        }catch (ParseException e){
            e.printStackTrace();
        }
        return null;
    }
}
