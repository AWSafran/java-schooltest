package com.lambdaschool.school.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambdaschool.school.SchoolApplicationTest;
import com.lambdaschool.school.model.Course;
import com.lambdaschool.school.model.Instructor;
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
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import java.util.ArrayList;
import java.util.List;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
@WebMvcTest(value = CourseControllerTest.class, secure = false)
@ContextConfiguration(classes = SchoolApplicationTest.class)
public class CourseControllerTest
{
    
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private CourseService courseService;
    
    private ArrayList<Course> courseList;
    
    @Before
    public void setUp() throws Exception
    {
        courseList = new ArrayList<>();
        Instructor i1 = new Instructor("Sally");
        Instructor i2 = new Instructor("Lucy");
        Instructor i3 = new Instructor("Charlie");
    
        Course c1 = new Course("Data Science", i1);
        Course c2 = new Course("JavaScript", i1);
        Course c3 = new Course("Node.js", i1);
        Course c4 = new Course("Java Back End", i2);
        Course c5 = new Course("Mobile IOS", i2);
        Course c6 = new Course("Mobile Android", i3);
        
        courseList.add(c1);
        courseList.add(c2);
        courseList.add(c3);
        courseList.add(c4);
        courseList.add(c5);
        courseList.add(c6);
    }
    
    @After
    public void tearDown() throws Exception
    {
    }
    
    @Test
    public void listAllCourses() throws Exception
    {
        String apiUrl = "/courses/courses";
    
        Mockito.when(courseService.findAll()).thenReturn(courseList);
    
        RequestBuilder rb = MockMvcRequestBuilders.get(apiUrl).accept(MediaType.APPLICATION_JSON);
        MvcResult r = mockMvc.perform(rb).andReturn();
        String tr = r.getResponse().getContentAsString();
    
        ObjectMapper mapper = new ObjectMapper();
        String er = mapper.writeValueAsString(courseList);
        
        assertEquals("Rest API Returns List", er, tr);
        
        
    }
    
    @Test
    public void addNewCourse() throws Exception
    {
        String apiUrl = "/courses/course/add";
        
        Instructor inst = new Instructor("Bob");
        
        Course course = new Course("Another TEst", inst);
        
        Mockito.when(courseService.save(any(Course.class))).thenReturn(course);
        
        ObjectMapper mapper = new ObjectMapper();
        String courseString = mapper.writeValueAsString(course);
    
        RequestBuilder rb = MockMvcRequestBuilders.get(apiUrl).accept(MediaType.APPLICATION_JSON);
        MvcResult r = mockMvc.perform(rb).andReturn();
        String tr = r.getResponse().getContentAsString();
        
        assertEquals("Rest API saves new course and returns it", courseString, tr);
    }
    
}