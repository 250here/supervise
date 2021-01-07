package com.supervise.tasksystem.dao;

import com.supervise.tasksystem.model.Expert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ExpertDaoTest {

    @Autowired
    ExpertDao expertDao;
    @BeforeEach
    void setUp() {
    }

    @Test
    void testFindById(){
        Expert expert=expertDao.findById(1).get();
        assertEquals("dog", expert.getExpertName());
        assertTrue(expertDao.existsById(1));
    }

    @AfterEach
    void tearDown() {
    }
}
