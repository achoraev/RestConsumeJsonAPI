package com.rest.consume.initial;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TaskControllersTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void getRequestTest() throws Exception {
        this.mvc.perform(get("/tasks")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void postRequestTest() throws Exception {
        String json = "{\n" +
                "\"tasks\":[\n" +
                "{\n" +
                "\"name\":\"task-1\",\n" +
                "\"command\":\"touch /tmp/file1\"\n" +
                "},\n" +
                "{\n" +
                "\"name\":\"task-2\",\n" +
                "\"command\":\"cat /tmp/file1\",\n" +
                "\"requires\":[\n" +
                "\"task-3\"\n" +
                "]\n" +
                "},\n" +
                "{\n" +
                "\"name\":\"task-3\",\n" +
                "\"command\":\"echo 'Hello World!' > /tmp/file1\",\n" +
                "\"requires\":[\n" +
                "\"task-1\"\n" +
                "]\n" +
                "},\n" +
                "{\n" +
                "\"name\":\"task-4\",\n" +
                "\"command\":\"rm /tmp/file1\",\n" +
                "\"requires\":[\n" +
                "\"task-2\",\n" +
                "\"task-3\"\n" +
                "]\n" +
                "}\n" +
                "]\n" +
                "}\n";
        this.mvc.perform(post("/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk());
    }

}
