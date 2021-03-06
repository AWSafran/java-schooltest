package com.lambdaschool.school.controller;

import com.lambdaschool.school.SchoolApplicationTest;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.number.OrderingComparison.lessThan;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = SchoolApplicationTest.class)
public class CourseControllerIntegrationTest
{
    @Autowired
    private WebApplicationContext webApplicationContext;
    
    @Before
    public void initializeRestAssuredMockMvcApplicationContext()
    {
        RestAssuredMockMvc.webAppContextSetup(webApplicationContext);
    }
    
    @Test
    public void addCourseTimeTest()
    {
        given().when().get("/courses/course/add").then().time(lessThan(5000L));
    }
    
    @Test
    public void whenMeasuredResponseTime()
    {
        given().when().get("/courses/courses").then().time(lessThan(5000L));
    }
}
