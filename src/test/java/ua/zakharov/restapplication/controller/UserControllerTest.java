package ua.zakharov.restapplication.controller;


import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ua.zakharov.restapplication.RestApplication;
import ua.zakharov.restapplication.aop.exception_handling.exception.UserNotFoundException;
import ua.zakharov.restapplication.model.User;
import ua.zakharov.restapplication.service.UserService;

import java.sql.Date;

@RunWith(SpringRunner.class)
@WebMvcTest(value = UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void getUserById_whenCorrectId() throws Exception {
        when(userService.getUserById(anyLong())).thenReturn(
                new User(1L, "Name", Date.valueOf("2000-12-12"))
        );

        mockMvc.perform(get("/api/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.equalTo(1)))
                .andExpect(jsonPath("$.name", Matchers.equalTo("Name")))
                .andExpect(jsonPath("$.age", Matchers.equalTo(22)));
    }

    @Test
    public void getUserById_whenInCorrectId() throws Exception {
        when(userService.getUserById(anyLong())).thenThrow(UserNotFoundException.class);

        mockMvc.perform(get("/api/users/{id}", "-1"))
                .andExpect(status().isBadRequest())
                .andExpect(result -> Assert.assertTrue(result.getResolvedException() instanceof UserNotFoundException));
    }

}