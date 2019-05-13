package com.lambdaschool.school.controller;

import com.lambdaschool.school.SchoolApplicationTest;
import com.lambdaschool.school.service.CourseService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@WebMvcTest(value = CourseControllerTest.class, secure = false)
@ContextConfiguration(classes = SchoolApplicationTest.class)
public class CourseControllerTest
{
    
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private CourseService courseService;
    
    @Before
    public void setUp() throws Exception
    {
    }
    
    @After
    public void tearDown() throws Exception
    {
    }
    
    @Test
    public void listAllCourses()
    {
        String apiUrl = "/courses";
    
        
    }
    
}