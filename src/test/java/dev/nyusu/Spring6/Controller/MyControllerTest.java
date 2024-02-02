package dev.nyusu.Spring6.Controller;

import dev.nyusu.Spring6.Controller.MyController;
import dev.nyusu.Spring6.Service.MyService;

import org.junit.jupiter.api.Test;

import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


// MyControllerTest.java
//@RunWith(SpringRunner.class)
@WebMvcTest(MyController.class)
public class MyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MyService myService;



    @Test
    public void testGreetEndpointSuccess() throws Exception {
        // Mocking the behavior of the service for a successful scenario
        String mockGreeting = "Hello, John!";
        Mockito.when(myService.generateGreeting("John")).thenReturn(mockGreeting);

        // Performing the request and verifying the response
        mockMvc.perform(get("/api/greet/{name}", "John"))
                .andExpect(status().isOk())
                .andExpect(content().string(mockGreeting));

        // Verifying that the service method was called with the correct parameter
        Mockito.verify(myService, Mockito.times(1)).generateGreeting("John");
    }

    @Test
    public void testGreetEndpointFailure() throws Exception {
        // Mocking the behavior of the service for a failure scenario
        Mockito.when(myService.generateGreeting(null)).thenThrow(new IllegalArgumentException("Name cannot be empty"));

        // Performing the request and verifying the response
        mockMvc.perform(get("/api/greet/{name}", ""))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Name cannot be empty"));

        // Verifying that the service method was called with the correct parameter
        Mockito.verify(myService, Mockito.times(1)).generateGreeting(null);
    }

    @org.junit.Test
    public void testGreetEndpointFailure2() throws Exception {

//        Mockito.when(myService.generateGreeting(null)).thenThrow(new Throwable(new NotFoundException("Resource not found")));

        // Performing the request and verifying the response
        mockMvc.perform(get("/api/greet/{name}", ""))
                .andExpect(status().isNotFound()) // Adjusted to expect 404 status
                .andExpect(content().string("dan"));

        // Verifying that the service method was called with the correct parameter
        Mockito.verify(myService, Mockito.times(1)).generateGreeting(null);
    }
}
