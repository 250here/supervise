package com.supervise.tasksystem.commandline.role;

import com.supervise.tasksystem.commandline.CommandLineInput;
import com.supervise.tasksystem.commandline.InfoSets;
import com.supervise.tasksystem.model.ExpertTaskItem;
import com.supervise.tasksystem.service.ExpertTaskItemService;
import com.supervise.tasksystem.service.ExpertTaskService;
import com.supervise.tasksystem.util.TextTreeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExpertCommandLine {
    @Autowired
    ExpertTaskService expertTaskService;
    @Autowired
    ExpertTaskItemService expertTaskItemService;
    @Autowired
    InfoSets infoSets;

    private int expertId;
    public void run(){
        String outputStr="";
        outputStr+="请输入ID";
        System.out.println(outputStr);
        expertId = CommandLineInput.chooseNumber(infoSets.getMarketIds());
        outputStr="";
        outputStr+="---专家主页---\n" +
                "选择功能:\n" +
                "0 退出\n" +
                "1 查看待完成任务\n" +
                "2 完成一项任务\n";
        while (true){
            System.out.print(outputStr);
            int command= CommandLineInput.chooseNumber(new int[]{0,1,2,3,4,5,6});
            switch (command){
                case 0:return;
                case 1:showUnfinishedTasks();break;
                case 2:finishOneTask();break;
                default:throw new RuntimeException();
            }
        }
    }
    private void showUnfinishedTasks(){
        List<ExpertTaskItem> expertTaskItems=expertTaskService.getUnfinishedExpertTaskItems(expertId);
        TextTreeGenerator text=new TextTreeGenerator();
        text.addLine("未完成任务");
        text.right();
        for(ExpertTaskItem expertTaskItem:expertTaskItems){
            text.addPair("Task Item ID",expertTaskItem.getExpertTaskItemId());
            text.addPair("Market ID",expertTaskItem.getMarket().getMarketId());
            text.addPair("Market Name",expertTaskItem.getMarket().getMarketName());
            text.addPair("Product Type ID",expertTaskItem.getProductType().getProductTypeId());
            text.addPair("Product Type Name",expertTaskItem.getProductType().getProductTypeName());
            text.addPair("DeadLine",expertTaskItem.getExpertTask().getExpertTaskGroup().getDeadline());
            text.addLine();
        }
        text.left();
        System.out.println(text.getText());
    }
    private void finishOneTask(){
        System.out.println("请输入任务项Id(Task Item ID):");
        int expertTaskItemId=CommandLineInput.nextPositiveIntOrZero();
        System.out.println("请输入不合格件数:");
        int unqualifiedNumber=CommandLineInput.nextPositiveIntOrZero();
        ExpertTaskItem expertTaskItem=expertTaskItemService.
                completeExpertTaskItem(expertId,expertTaskItemId,unqualifiedNumber);
        if(expertTaskItem==null){
            System.out.println("ID不正确，操作失败");
        }else{
            System.out.println("操作成功");
        }
    }
}
