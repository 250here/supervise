package com.supervise.tasksystem.commandline.role;

import com.supervise.tasksystem.commandline.CommandLineInput;
import com.supervise.tasksystem.commandline.InfoSets;
import com.supervise.tasksystem.dao.MarketDao;
import com.supervise.tasksystem.model.Market;
import com.supervise.tasksystem.model.MarketTaskItem;
import com.supervise.tasksystem.service.MarketTaskItemService;
import com.supervise.tasksystem.service.MarketTaskService;
import com.supervise.tasksystem.util.TextTreeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MarketCommandLine {
    private int marketId=-1;
    @Autowired
    MarketTaskService marketTaskService;
    @Autowired
    MarketTaskItemService marketTaskItemService;
    @Autowired
    MarketDao marketDao;
    @Autowired
    InfoSets infoSets;
    public void run(){
        String outputStr="";
        outputStr+="请输入ID";
        System.out.println(outputStr);
        marketId= CommandLineInput.chooseNumber(infoSets.getMarketIds());
        outputStr="";
        outputStr+="---市场主页---\n" +
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
        List<MarketTaskItem> marketTaskItems=marketTaskService.getUnfinishedMarketTaskItems(marketId);
        TextTreeGenerator text=new TextTreeGenerator();
        text.addLine("未完成任务");
        text.right();
        for(MarketTaskItem marketTaskItem:marketTaskItems){
            text.addPair("Task Item ID",marketTaskItem.getMarketTaskItemId());
            text.addPair("Market Name",marketDao.findById(marketId).get().getMarketName());
            text.addPair("Product Type ID",marketTaskItem.getProductType().getProductTypeId());
            text.addPair("Product Type Name",marketTaskItem.getProductType().getProductTypeName());
            text.addPair("DeadLine",marketTaskItem.getMarketTask().getMarketTaskGroup().getDeadline());
            text.addLine();
        }
        text.left();
        System.out.println(text.getText());
    }
    private void finishOneTask(){
        System.out.println("请输入任务项Id(Task Item ID):");
        int marketTaskItemId=CommandLineInput.nextPositiveIntOrZero();
        System.out.println("请输入不合格件数:");
        int unqualifiedNumber=CommandLineInput.nextPositiveIntOrZero();
        MarketTaskItem marketTaskItem=marketTaskItemService.
                completeMarketTaskItem(marketId,marketTaskItemId,unqualifiedNumber);
        if(marketTaskItem==null){
            System.out.println("ID不正确，操作失败");
        }else{
            System.out.println("操作成功");
        }
    }
}
