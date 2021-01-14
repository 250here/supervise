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
    @Autowired
    ProductTypeDao productTypeDao;
    @Autowired
    MarketDao marketDao;

    public ExpertTaskItem addExpertTaskItem(int expertTaskId, int productTypeId, int marketId){        //添加检测项
        ExpertTask expertTask = expertTaskDao.findById(expertTaskId).get();
        ProductType productType = productTypeDao.findById(productTypeId).get();
        Market market = marketDao.findById(marketId).get();

        ExpertTaskItem expertTaskItem = new ExpertTaskItem();
        expertTaskItem.setFinished(false);
        expertTaskItem.setExpertTask(expertTask);
        expertTaskItem.setProductType(productType);
        expertTaskItem.setMarket(market);
        expertTaskItemDao.save(expertTaskItem);

        return expertTaskItem;
    }

    public int getUnqualifiedNumberInTask(int expertTaskId){           //查看任务下不合格数
        ExpertTask expertTask = expertTaskDao.findById(expertTaskId).get();
        List<ExpertTaskItem> expertTaskItemList = expertTask.getExpertTaskItems();
        int num = 0;
        for(ExpertTaskItem item: expertTaskItemList){
            num += item.getUnqualifiedNumber();
        }
        return num;
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

    public String grade(int expertTaskId){          //查看某专家得分情况
        Date time = VirtualTime.getDate();
        Optional<ExpertTask> expertTaskOptional = expertTaskDao.findById(expertTaskId);
        ExpertTask expertTask = expertTaskOptional.isPresent()?expertTaskOptional.get() : null;
        int grade = 0;
        String record = "";

        if(expertTask == null){
            record += "无得分记录,得分：0";
            return record;
        }
        Expert expert = expertTask.getExpert();
        ExpertTaskGroup expertTaskGroup = expertTaskGroupDao.findById(expertTask.getExpertTaskGroup().getExpertTaskGroupId()).get();
        List<ExpertTaskItem> expertTaskItemList = expertTask.getExpertTaskItems();

        if(hasUnfinishedItem(expertTask.getExpertTaskId())==false && getLatestDate(expertTask.getExpertTaskId()).compareTo(expertTaskGroup.getDeadline())!=1){
            grade +=10;
            record += expert.getExpertName() + "按时完成，得分：" + grade + "\n";
//            System.out.println(market.getMarketName() +" 得分：" + grade);
        }
        if((hasUnfinishedItem(expertTask.getExpertTaskId())==false && getLatestDate(expertTask.getExpertTaskId()).getTime() - expertTaskGroup.getDeadline().getTime()>0)          //未按时完成
                || (hasUnfinishedItem(expertTask.getExpertTaskId())==true && time.getTime() - expertTaskGroup.getDeadline().getTime() > 0) ){
            grade -= 10;

            record += expert.getExpertName() + "未按时完成，扣10分，得分：" + grade + "\n";
        }
        if((hasUnfinishedItem(expertTask.getExpertTaskId())==false &&getLatestDate(expertTask.getExpertTaskId()).getTime() - expertTaskGroup.getDeadline().getTime() > 1728000000)
                ||(hasUnfinishedItem(expertTask.getExpertTaskId())==true && time.getTime() - expertTaskGroup.getDeadline().getTime() > 1728000000)){  //完成时间超过20天
            grade -= 20;
            record += expert.getExpertName() + "超20天未完成，扣20分，得分：" + grade + "\n";
        }
        if(record.equals("")){
            record += "无得分记录，得分：0";
        }
        return record;
    }

    public boolean hasUnfinishedItem(int expertTaskId){          //是否有未完成的类别
        ExpertTask expertTask = expertTaskDao.findById(expertTaskId).get();
        List<ExpertTaskItem> expertTaskItemList = expertTaskItemDao.findByExpertTask(expertTask);
        for (ExpertTaskItem item : expertTaskItemList){
            if(item.isFinished() == false){
                return true;
            }
        }
        return false;
    }

    public Date getLatestDate(int expertTaskId){             //得到最后完成时间
        ExpertTask expertTask = expertTaskDao.findById(expertTaskId).get();
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
