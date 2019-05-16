package com.lambdaschool.school.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambdaschool.school.SchoolApplicationTest;
import com.lambdaschool.school.SchoolApplicationTests;
import com.lambdaschool.school.model.Course;
import com.lambdaschool.school.model.Instructor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityNotFoundException;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SchoolApplicationTest.class)
public class CourseServiceImplTest
{
    
    @Autowired
    private CourseService courseService;
    
    @Autowired
    private InstructorService instructorService;
    
    @Before
    public void setUp() throws Exception
    {
        MockitoAnnotations.initMocks(this);
    }
    
    @After
    public void tearDown() throws Exception
    {
    }
    
    @Test
    public void deleteFound()
    {
        courseService.delete(4);
        assertEquals(5, courseService.findAll().size());
    }
    
    @Test (expected = EntityNotFoundException.class)
    public void deleteNotFound()
    {
        courseService.delete(399);
        assertEquals(6, courseService.findAll().size());
    }
    
    @Test
    public void findCourseById()
    {
        assertEquals("JavaScript", courseService.findCourseById(2).getCoursename());
    }
    
    @Test
    public void save()
    {
        Instructor newInst = new Instructor("Mike");
        
        instructorService.save(newInst);
    
        Course newCourse = new Course("Testing", newInst);
        
        Course addedCourse = courseService.save(newCourse);
        
        Course foundCourse = courseService.findCourseById(addedCourse.getCourseid());
        
        assertEquals(addedCourse.getCourseid(), foundCourse.getCourseid());
    }
}