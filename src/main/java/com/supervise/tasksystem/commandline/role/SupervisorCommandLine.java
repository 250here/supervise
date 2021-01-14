package com.supervise.tasksystem.commandline.role;

import com.supervise.tasksystem.commandline.CommandLineInput;
import com.supervise.tasksystem.commandline.InfoSets;
import com.supervise.tasksystem.model.*;
import com.supervise.tasksystem.service.*;
import com.supervise.tasksystem.util.TextTreeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class SupervisorCommandLine {
    @Autowired
    ExpertTaskGroupService expertTaskGroupService;
    @Autowired
    ExpertTaskService expertTaskService;
    @Autowired
    MarketTaskGroupService marketTaskGroupService;
    @Autowired
    MarketTaskService marketTaskService;
    @Autowired
    ProductTypeService productTypeService;
    @Autowired
    InfoSets infoSets;
    public void run(){
        String outputStr="";
        outputStr+="---监管局主页---\n" +
                "选择功能:\n" +
                "0 退出\n" +
                "1 显示所有任务\n" +
                "2 向一组市场发起监管任务\n" +
                "3 向一组专家发起监管任务\n" +
                "4 查看某个农贸产品类别在某个时间范围内的总的不合格数\n" +
                "5 查看某个市场的评估\n" +
                "6 查看某个专家的评估\n" +
                "";
        while (true){
            System.out.print(outputStr);
            int command= CommandLineInput.chooseNumber(new int[]{0,1,2,3,4,5,6});
            switch (command){
                case 0:return;
                case 1:showAllTasks();break;
                case 2:createMarketTask();break;
                case 3:createExpertTask();break;
                case 4:showUnqualifIedNumberOfOneProductType();break;
                case 5:showGradeOfOneMarket();break;
                case 6:showGradeOfOneExpert();break;
                default:throw new RuntimeException();
            }
        }
    }
    private void showAllTasks(){
        TextTreeGenerator text=new TextTreeGenerator();
        text.addLine("专家任务：");
        List<ExpertTaskGroup> expertTaskGroups=expertTaskGroupService.getAllExpertTaskGroup();
        text.right();
        for(ExpertTaskGroup expertTaskGroup:expertTaskGroups){
            text.addPair("任务组ID",expertTaskGroup.getExpertTaskGroupId());
            text.addPair("截止日期",expertTaskGroup.getDeadline());
            text.addPair("任务组描述",expertTaskGroup.getTaskDescription());
            text.right();
            text.addLine();
            for(ExpertTask expertTask:expertTaskGroup.getExpertTasks()){
                text.addPair("任务ID",expertTask.getExpertTaskId());
                text.addPair("专家ID",expertTask.getExpert().getExpertId());
                text.addPair("专家",expertTask.getExpert().getExpertName());
                text.addPair("是否完成",expertTask.isFinished());
                text.addLine();
                text.right();
                for(ExpertTaskItem expertTaskItem:expertTask.getExpertTaskItems()){
                    text.addPair("任务项ID",expertTaskItem.getExpertTaskItemId());
                    text.addPair("产品种类",expertTaskItem.getProductType().getProductTypeName());
                    text.addPair("市场ID",expertTaskItem.getMarket().getMarketId());
                    text.addPair("是否完成",expertTaskItem.isFinished());
                    if(expertTaskItem.isFinished()){
                        text.addPair("完成日期",expertTaskItem.getFinishDate());
                        text.addPair("不合格数",expertTaskItem.getUnqualifiedNumber());
                    }
                }
                text.left();
            }
            text.left();
        }
        text.left();
        System.out.print(text.getText());


        text=new TextTreeGenerator();
        text.addLine("市场任务：");
        List<MarketTaskGroup> marketTaskGroups=marketTaskGroupService.getAllMarketTaskGroup();
        text.right();
        for(MarketTaskGroup marketTaskGroup:marketTaskGroups){
            text.addPair("任务组ID",marketTaskGroup.getMarketTaskGroupId());
            text.addPair("截止日期",marketTaskGroup.getDeadline());
            text.addPair("任务组描述",marketTaskGroup.getTaskDescription());
            text.right();
            text.addLine();
            for(MarketTask marketTask:marketTaskGroup.getMarketTasks()){
                text.addPair("任务ID",marketTask.getMarketTaskId());
                text.addPair("专家ID",marketTask.getMarket().getMarketId());
                text.addPair("专家",marketTask.getMarket().getMarketName());
                text.addPair("是否完成",marketTask.isFinished());
                text.addLine();
                text.right();
                for(MarketTaskItem marketTaskItem:marketTask.getMarketTaskItems()){
                    text.addPair("任务项ID",marketTaskItem.getMarketTaskItemId());
                    text.addPair("产品种类",marketTaskItem.getProductType().getProductTypeName());
                    text.addPair("是否完成",marketTaskItem.isFinished());
                    if(marketTask.isFinished()){
                        text.addPair("完成日期",marketTaskItem.getFinishDate());
                        text.addPair("不合格数",marketTaskItem.getUnqualifiedNumber());
                    }
                    text.addLine();
                }
                text.left();
            }
            text.left();
        }
        text.left();
        System.out.print(text.getText());

    }
    private void createMarketTask(){
        System.out.println("现在创建市场任务组。请输入截止日期：");
        Date deadline=CommandLineInput.nextFutureDate();
        MarketTaskGroup marketTaskGroup=marketTaskGroupService.makeMarketTaskGroup(deadline);
        int marketTaskGroupId=marketTaskGroup.getMarketTaskGroupId();
        System.out.println("市场任务组创建成功。现在加入任务：");
        List<Integer> selectedMarketIds=new ArrayList<>();
        while(true){
            if(selectedMarketIds.size()== infoSets.getMarketIds().length){
                System.out.println("已经全部选中,不能继续选择");
                break;
            }
            System.out.println("请从以下市场ID中选择一个市场：");
            int marketId=CommandLineInput.chooseNumber(infoSets.getMarketIds());
            if(selectedMarketIds.contains(marketId)){
                System.out.println("已经选择过");
                continue;
            }
            selectedMarketIds.add(marketId);

            int marketTaskId=marketTaskGroupService.addMarketTask(marketTaskGroupId,marketId)
                    .getMarketTaskId();

            TextTreeGenerator text=new TextTreeGenerator();
            text.addLine("请选择检查的产品类型：");
            text.right();
            for(ProductType productType: infoSets.getProducts()){
                text.addPair("ID",productType.getProductTypeId());
                text.addPair("名称",productType.getProductTypeName());
                text.addLine();
            }
            text.left();
            System.out.println(text.getText());
            List<Integer> productTypeIds=new ArrayList<>();
            while(true){
                if(productTypeIds.size()==infoSets.getProductIds().length){
                    System.out.println("已经全部选中,不能继续选择");
                    break;
                }
                System.out.println("请从以下产品种类ID中选择一个：");
                int productTypeId=CommandLineInput.chooseNumber(infoSets.getProductIds());
                if(productTypeIds.contains(productTypeId)){
                    System.out.println("已经选择过");
                    continue;
                }
                productTypeIds.add(productTypeId);

                marketTaskService.addMarketTaskItem(marketTaskId,productTypeId);

                System.out.println("是否继续添产品种类？输入0表示否，1表示是：");
                int anwser=CommandLineInput.chooseNumber(new int[]{0,1});
                if(anwser==0){
                    break;
                }
            }
            System.out.println("是否继续添加市场任务？输入0表示否，1表示是：");
            int anwser=CommandLineInput.chooseNumber(new int[]{0,1});
            if(anwser==0){
                break;
            }
        }

    }
    private void createExpertTask(){
        System.out.println("现在创建专家任务组。请输入截止日期：");
        Date deadline=CommandLineInput.nextFutureDate();
        ExpertTaskGroup expertTaskGroup=expertTaskGroupService.makeExpertTaskGroup(deadline);
        int expertTaskGroupId=expertTaskGroup.getExpertTaskGroupId();
        System.out.println("专家任务组创建成功。现在加入任务：");
        List<Integer> selectedExpertIds=new ArrayList<>();
        while(true){
            if(selectedExpertIds.size()== infoSets.getExpertIds().length){
                System.out.println("已经全部选中,不能继续选择");
                break;
            }
            System.out.println("请从以下专家ID中选择一个：");
            int expertId=CommandLineInput.chooseNumber(infoSets.getExpertIds());
            if(selectedExpertIds.contains(expertId)){
                System.out.println("已经选择过");
                continue;
            }
            selectedExpertIds.add(expertId);

            int expertTaskId=expertTaskGroupService.addExpertTask(expertTaskGroupId,expertId)
                    .getExpertTaskId();

            TextTreeGenerator text=new TextTreeGenerator();
            text.addLine("请选择检查的产品类型：");
            text.right();
            for(ProductType productType: infoSets.getProducts()){
                text.addPair("ID",productType.getProductTypeId());
                text.addPair("名称",productType.getProductTypeName());
                text.addLine();
            }
            text.left();
            System.out.println(text.getText());
            List<String> productTypeId_markrtIds=new ArrayList<>();
            while(true){
                if(productTypeId_markrtIds.size()==infoSets.getProductIds().length*infoSets.getMarketIds().length){
                    System.out.println("已经全部选中,不能继续选择");
                    break;
                }
                System.out.println("请从以下产品种类ID中选择一个：");
                int productTypeId=CommandLineInput.chooseNumber(infoSets.getProductIds());
                System.out.println("请从以下市场ID中选择一个：");
                int marketId=CommandLineInput.chooseNumber(infoSets.getProductIds());
                String productTypeId_markrtId=productTypeId+"_"+marketId;
                if(productTypeId_markrtIds.contains(productTypeId_markrtId)){
                    System.out.println("已经选择过");
                    continue;
                }
                productTypeId_markrtIds.add(productTypeId_markrtId);

                expertTaskService.addExpertTaskItem(expertTaskId,productTypeId,marketId);

                System.out.println("是否继续添加产品种类和市场？输入0表示否，1表示是：");
                int anwser=CommandLineInput.chooseNumber(new int[]{0,1});
                if(anwser==0){
                    break;
                }
            }
            System.out.println("是否继续添加专家任务？输入0表示否，1表示是：");
            int anwser=CommandLineInput.chooseNumber(new int[]{0,1});
            if(anwser==0){
                break;
            }
        }
    }
    private void showUnqualifIedNumberOfOneProductType(){
        TextTreeGenerator text=new TextTreeGenerator();
        text.addLine("请选择产品类型：");
        text.right();
        for(ProductType productType: infoSets.getProducts()){
            text.addPair("ID",productType.getProductTypeId());
            text.addPair("名称",productType.getProductTypeName());
            text.addLine();
        }
        text.left();
        System.out.println(text.getText());
        int productTypeId=CommandLineInput.chooseNumber(infoSets.getProductIds());
        System.out.println("请输入开始时间：");
        Date startDate=CommandLineInput.nextDate();
        System.out.println("请输入结束时间：");
        Date endDate=CommandLineInput.nextDate();
        int number=productTypeService.getUnqualityNumberOfType(startDate,endDate,productTypeId);
        System.out.println("该产品在"+startDate+"和"+endDate+"之间的检测结果中共有"+number+"不合格数.");
    }
    private void showGradeOfOneMarket(){
        System.out.println("请选择市场Id");
        int marketId=CommandLineInput.chooseNumber(infoSets.getMarketIds());
        String gradeAndRecord=marketTaskService.grade(marketId);
        System.out.println(gradeAndRecord);
    }
    private void showGradeOfOneExpert(){
        System.out.println("请选择专家Id");
        int expertId=CommandLineInput.chooseNumber(infoSets.getExpertIds());
        String gradeAndRecord=expertTaskService.grade(expertId);
        System.out.println(gradeAndRecord);
    }
}
