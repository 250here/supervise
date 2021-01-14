package com.supervise.tasksystem.service;

import com.supervise.tasksystem.dao.ExpertTaskGroupDao;
import com.supervise.tasksystem.dao.MarketTaskGroupDao;
import com.supervise.tasksystem.dao.ProductTypeDao;
import com.supervise.tasksystem.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProductTypeService {
    @Autowired
    ProductTypeDao productTypeDao;
    @Autowired
    InformationService informationService;
    @Autowired
    MarketTaskGroupDao marketTaskGroupDao;
    @Autowired
    ExpertTaskGroupDao expertTaskGroupDao;

    public int getUnqualityNumberOfType(Date startTime, Date endTime, int productId){
        List<MarketTaskGroup> marketTaskGroupList = marketTaskGroupDao.findAll();
        List<ExpertTaskGroup> expertTaskGroupList = expertTaskGroupDao.findAll();
        int totalNum = 0;

        for (MarketTaskGroup group : marketTaskGroupList){            //一段时间内市场任务中所有不合格数
            List<MarketTask> marketTaskList = group.getMarketTasks();
            for (MarketTask marketTask: marketTaskList){
                for (MarketTaskItem marketTaskItem : marketTask.getMarketTaskItems()){

                    if (marketTaskItem.isFinished() == true && duringDate(startTime,endTime,marketTaskItem.getFinishDate())){
                        totalNum += marketTaskItem.getUnqualifiedNumber();
                    }
                }
            }
        }

        for (ExpertTaskGroup group : expertTaskGroupList){            //一段时间内专家任务中所有不合格数
            List<ExpertTask> expertTaskList = group.getExpertTasks();
            for (ExpertTask expertTask: expertTaskList){
                    for (ExpertTaskItem expertTaskItem : expertTask.getExpertTaskItems()){

                    if (expertTaskItem.isFinished() == true && duringDate(startTime,endTime,expertTaskItem.getFinishDate())){
                        totalNum += expertTaskItem.getUnqualifiedNumber();
                    }
                }
            }
        }
        return totalNum;
    }

    public boolean duringDate(Date startTime, Date endTime, Date date){
        if(date.compareTo(startTime)>=0 && date.compareTo(endTime)<=0){
            return true;
        }
        return false;
    }
}
