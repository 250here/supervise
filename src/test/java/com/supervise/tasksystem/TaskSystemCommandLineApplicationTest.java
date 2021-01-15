package com.supervise.tasksystem;

import com.supervise.tasksystem.commandline.CommandLine;
import com.supervise.tasksystem.commandline.CommandLineInput;
import com.supervise.tasksystem.model.*;
import com.supervise.tasksystem.service.ExpertTaskGroupService;
import com.supervise.tasksystem.service.MarketTaskGroupService;
import com.supervise.tasksystem.service.ProductTypeService;
import com.supervise.tasksystem.util.VirtualTime;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.File;
import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
//不能一同运行
class TaskSystemCommandLineApplicationTest {
    @Autowired
    ExpertTaskGroupService expertTaskGroupService;
    @Autowired
    MarketTaskGroupService marketTaskGroupService;
    @Autowired
    ProductTypeService productTypeService;
    @Autowired
    CommandLine commandLine;
    @BeforeEach
    void setUp() {
    }

    @Test
    void test1(){
        changeInputStream(1);
        commandLine.run();
        List<MarketTask> marketTasks=marketTaskGroupService.getMarketTasks(2);
        MarketTaskItem marketTaskItem=marketTasks.get(0).getMarketTaskItems().get(0);
        assertEquals(1,marketTaskItem.getProductType().getProductTypeId());
        assertEquals(13,marketTaskItem.getUnqualifiedNumber());
        assertTrue(marketTaskItem.isFinished());
    }

    @Test
    void test2(){
        changeInputStream(2);
        commandLine.run();
        ExpertTask expertTask=expertTaskGroupService.getExperttTasks(2).get(0);
        ExpertTaskItem expertTaskItem2=expertTask.getExpertTaskItems().get(0);
        ExpertTaskItem expertTaskItem3=expertTask.getExpertTaskItems().get(1);
        if(expertTaskItem2.getExpertTaskItemId()==3){
            ExpertTaskItem expertTaskItem=expertTaskItem2;
            expertTaskItem2=expertTaskItem3;
            expertTaskItem3=expertTaskItem;
        }
        assertFalse(expertTaskItem2.isFinished());
        assertTrue(expertTaskItem3.isFinished());
        assertEquals("蔬菜类",expertTaskItem3.getProductType().getProductTypeName());
        assertEquals(15,expertTaskItem3.getUnqualifiedNumber());
    }


    @Test
    void test3() throws ParseException {
        changeInputStream(3);
        commandLine.run();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        Date startDate= simpleDateFormat.parse("2021-1-5");
        Date endDate= simpleDateFormat.parse("2021-1-15");
        int num=productTypeService.getUnqualityNumberOfType(startDate,endDate,1);
        assertEquals(13,num);

        startDate= simpleDateFormat.parse("2021-1-4");
        endDate= simpleDateFormat.parse("2021-12-12");
        num=productTypeService.getUnqualityNumberOfType(startDate,endDate,1);
        assertEquals(28,num);
    }

    @Test
    void test4(){
        changeInputStream(4);
        commandLine.run();

    }

    @AfterEach
    void tearDown() {
    }
    private void changeInputStream(int i){
        try{
            FileInputStream fis=new FileInputStream("src\\main\\resources\\testcase\\testcase"+i+".txt");
            //if(new File("testcase"+i+".txt").exists()){System.exit(0);}
            System.setIn(fis);
            CommandLineInput.resetInput();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
