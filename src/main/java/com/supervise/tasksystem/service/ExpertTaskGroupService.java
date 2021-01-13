package com.supervise.tasksystem.service;

import com.supervise.tasksystem.dao.ExpertDao;
import com.supervise.tasksystem.dao.ExpertTaskDao;
import com.supervise.tasksystem.dao.ExpertTaskGroupDao;
import com.supervise.tasksystem.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ExpertTaskGroupService {
    @Autowired
    ExpertTaskGroupDao expertTaskGroupDao;
    @Autowired
    ExpertTaskDao expertTaskDao;
    @Autowired
    ExpertDao expertDao;

    public List<ExpertTask> getUnfinishedExpertTasks(int expertTaskGroupId){       //查找一组专家监管任务下未完成的任务
        Optional<ExpertTaskGroup> optionalExpertTaskGroup = expertTaskGroupDao.findById(expertTaskGroupId);
        ExpertTaskGroup expertTaskGroup = optionalExpertTaskGroup.isPresent()?optionalExpertTaskGroup.get() : null;
        if(expertTaskGroup == null){
            System.out.println("查询数据为空");
            return null;
        }
        return expertTaskDao.findByExpertTaskGroupAndIsFinishedFalse(expertTaskGroup);
    }
    public ExpertTaskGroup makeExpertTaskGroup(Date deadline){          //新建任务组
        ExpertTaskGroup expertTaskGroup = new ExpertTaskGroup();
        expertTaskGroup.setDeadline(deadline);
        expertTaskGroup.setExpertTasks(new ArrayList<ExpertTask>());
        expertTaskGroupDao.save(expertTaskGroup);

        return expertTaskGroup;
    }

    public ExpertTask addExpertTask(int expertTaskGroupId, int expertId, List<ExpertTaskItem> itemList){
        ExpertTaskGroup expertTaskGroup = expertTaskGroupDao.findById(expertTaskGroupId).get();
        Expert expert = expertDao.findById(expertId).get();
        ExpertTask expertTask = new ExpertTask();

        expertTask.setFinished(false);
        expertTask.setExpertTaskGroup(expertTaskGroup);
        expertTask.setExpert(expert);
        expertTask.setExpertTaskItems(itemList);

        expertTaskDao.save(expertTask);
        return expertTask;
    }

    public ExpertTask addExpertTask(ExpertTaskGroup expertTaskGroup, Expert expert, List<ExpertTaskItem> itemList){    //添加监管任务
        ExpertTask expertTask = new ExpertTask();
        expertTask.setFinished(false);
        expertTask.setExpertTaskGroup(expertTaskGroup);
        expertTask.setExpert(expert);
        expertTask.setExpertTaskItems(itemList);

        expertTaskDao.save(expertTask);
        return expertTask;
    }

    public List<ExpertTask> getExperttTasks(int expertTaskGroupId){          //获得任务组下的所有任务
        ExpertTaskGroup expertTaskGroup = expertTaskGroupDao.findById(expertTaskGroupId).get();
        return expertTaskGroup.getExpertTasks();
    }

    public List<ExpertTaskGroup> getAllExpertTaskGroup(){          //获得所有专家任务组
        List<ExpertTaskGroup> expertTaskGroups = expertTaskGroupDao.findAll();

        return expertTaskGroups;
    }

    public int getUnqualifiedNumberInGroup(int expertTaskGroupId){              //得到专家任务组下所有不合格数
        ExpertTaskGroup expertTaskGroup = expertTaskGroupDao.findById(expertTaskGroupId).get();
        List<ExpertTask> expertTaskList = expertTaskGroup.getExpertTasks();
        int num = 0;
        for (ExpertTask expertTask : expertTaskList){
            for (ExpertTaskItem expertTaskItem : expertTask.getExpertTaskItems()){
                num += expertTaskItem.getUnqualifiedNumber();
            }
        }
        return num;
    }
}
