package com.supervise.tasksystem;

import com.supervise.tasksystem.commandline.CommandLine;
import com.supervise.tasksystem.commandline.CommandLineInput;
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

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class TaskSystemCommandLineApplicationTest {

    @Autowired
    CommandLine commandLine;
    @BeforeEach
    void setUp() {
    }

    @Test
    @Order(1)
    void test(){
        changeInputStream(1);
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
