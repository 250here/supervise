package com.supervise.tasksystem.service;

import com.supervise.tasksystem.dao.ExpertTaskDao;
import com.supervise.tasksystem.dao.ExpertTaskGroupDao;
import com.supervise.tasksystem.model.Expert;
import com.supervise.tasksystem.model.ExpertTask;
import com.supervise.tasksystem.model.ExpertTaskGroup;
import com.supervise.tasksystem.model.ExpertTaskItem;
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

    public List<ExpertTask> getUnfinishedExpertTasks(int expertTaskGroupId){       //查找一组专家监管任务下未完成的任务
        Optional<ExpertTaskGroup> optionalExpertTaskGroup = expertTaskGroupDao.findById(expertTaskGroupId);
        ExpertTaskGroup expertTaskGroup = optionalExpertTaskGroup.isPresent()?optionalExpertTaskGroup.get() : null;
        if(expertTaskGroup == null){
            System.out.println("查询数据为空");
            return null;
        }
        return expertTaskDao.findByExpertTaskGroupAndIsFinishedFalse(expertTaskGroup);
    }
    public void makeExpertTaskGroup(Date deadline){          //新建任务组
        ExpertTaskGroup expertTaskGroup = new ExpertTaskGroup();
        expertTaskGroup.setDeadline(deadline);
        expertTaskGroup.setExpertTasks(new ArrayList<ExpertTask>());
        expertTaskGroupDao.save(expertTaskGroup);
    }

    public void addExpertTask(ExpertTaskGroup expertTaskGroup, Expert expert, List<ExpertTaskItem> itemList){    //添加监管任务
        ExpertTask expertTask = new ExpertTask();
        expertTask.setFinished(false);
        expertTask.setExpertTaskGroup(expertTaskGroup);
        expertTask.setExpert(expert);
        expertTask.setExpertTaskItems(itemList);

        expertTaskDao.save(expertTask);
    }

    public List<ExpertTask> getExperttTasks(ExpertTaskGroup expertTaskGroup){          //获得任务组下的所有任务
        return expertTaskGroup.getExpertTasks();
    }
}
