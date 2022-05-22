package com.example.safecarrier;

import com.example.safecarrier.dto.AllResponse;
import com.example.safecarrier.dto.UploadDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class DataControllerTest {
//    @Autowired
//    private MockMvc mockMvc;
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @Test
//    void uploadEncryptedData() throws Exception {
//        byte[] data={'a','b'};
//
//        String jsonToString = objectMapper.writeValueAsString(new UploadDto(data,"TEXT",3,"https://safecarrier.page.link/QW5DR2emNLrRyAVZ8","47dsfdfs","테스트"));
//        mockMvc.perform(post("/data")
//                .content(jsonToString)
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    void getDataByLink() throws Exception{
//        mockMvc.perform(get("/data/47dsfdfs")
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andDo(print());
//    }
//
//    @Test
//    void getLeftReadCount() throws Exception{
//        MvcResult result = mockMvc.perform(get("/data/read/47dsfdfs")
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andDo(print())
//                .andReturn();
//    }
//
//    @Test
//    void getLeftReadCountZero() throws Exception{
//        MvcResult result = mockMvc.perform(get("/data/read/47dsfdfs").accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andDo(print())
//                .andReturn();
//    }
//
//    @Test
//    void getAllSendData() throws Exception{
//        String ids="3,4,5";
//        MvcResult result = mockMvc.perform(get("/data")
//                        .accept(MediaType.APPLICATION_JSON)
//                        .param("id", ids))
//                .andExpect(status().isOk())
//                .andDo(print())
//                .andReturn();
//        List<AllResponse> allResponses = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<AllResponse>>() {
//        });
//    }
}