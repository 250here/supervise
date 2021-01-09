package com.supervise.tasksystem.service;

import com.supervise.tasksystem.dao.*;
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
public class ExpertTaskService {
    @Autowired
    ExpertDao expertDao;
    @Autowired
    ExpertTaskDao expertTaskDao;
    @Autowired
    ExpertTaskGroupDao expertTaskGroupDao;
    @Autowired
    ExpertTaskItemDao expertTaskItemDao;

    public void addExpertTaskItem(ExpertTask expertTask, ProductType productType){        //添加检测项

        ExpertTaskItem expertTaskItem = new ExpertTaskItem();
        expertTaskItem.setFinished(false);
        expertTaskItem.setExpertTask(expertTask);
        expertTaskItem.setProductType(productType);
        expertTaskItemDao.save(expertTaskItem);
    }

    public List<ExpertTaskItem> getUnfinishedExpertTaskItems(int expertTaskId){                 //查找某专家任务下未完成的类别
        Optional<ExpertTask> expertTaskOptional = expertTaskDao.findById(expertTaskId);
        ExpertTask expertTask = expertTaskOptional.isPresent()?expertTaskOptional.get() : null;
        if(expertTask == null){
            System.out.println("查询数据为空");
            return null;
        }
        return expertTaskItemDao.findByExpertTaskAndIsFinishedFalse(expertTask);
    }

    public int grade(int expertTaskId, VirtualTime time){          //查看某专家得分情况
        ExpertTask expertTask = expertTaskDao.findById(expertTaskId).get();
        Expert expert = expertTask.getExpert();
        ExpertTaskGroup expertTaskGroup = expertTaskGroupDao.findById(expertTask.getExpertTaskGroup().getExpertTaskGroupId()).get();
        List<ExpertTaskItem> expertTaskItemList = expertTask.getExpertTaskItems();
        int grade = 0;
        if(hasUnfinishedItem(expertTask)==false && getLatestDate(expertTask).compareTo(expertTaskGroup.getDeadline())!=1){
            grade +=10;
//            System.out.println(market.getMarketName() +" 得分：" + grade);
        }
        if((hasUnfinishedItem(expertTask)==false && getLatestDate(expertTask).getTime() - expertTaskGroup.getDeadline().getTime()>0)          //未按时完成
                || (hasUnfinishedItem(expertTask)==true && time.getDate().getTime() - expertTaskGroup.getDeadline().getTime() > 0) ){
            grade -= 10;
        }
        if((hasUnfinishedItem(expertTask)==false &&getLatestDate(expertTask).getTime() - expertTaskGroup.getDeadline().getTime() > 1728000000)
                ||(hasUnfinishedItem(expertTask)==true && time.getDate().getTime() - expertTaskGroup.getDeadline().getTime() > 1728000000)){  //完成时间超过20天
            grade -= 20;
        }
        return grade;
    }

    public boolean hasUnfinishedItem(ExpertTask expertTask){          //是否有未完成的类别
        List<ExpertTaskItem> expertTaskItemList = expertTaskItemDao.findByExpertTask(expertTask);
        for (ExpertTaskItem item : expertTaskItemList){
            if(item.isFinished() == false){
                return true;
            }
        }
        return false;
    }

    public Date getLatestDate(ExpertTask expertTask){             //得到最后完成时间
        List<ExpertTaskItem> expertTaskItemList = expertTask.getExpertTaskItems();
        String s = "1999-01-01 00:00:00";
        Date date;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            date = simpleDateFormat.parse(s);

            for(ExpertTaskItem item : expertTaskItemList){
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
