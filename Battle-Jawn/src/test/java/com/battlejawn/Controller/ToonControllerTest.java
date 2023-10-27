package com.battlejawn.Controller;

import com.battlejawn.Controllers.ToonController;
import com.battlejawn.Entities.Hero.Caster;
import com.battlejawn.Service.ToonService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;

import java.awt.*;

import static java.nio.file.Paths.get;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@WebMvcTest(controllers = ToonController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class ToonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ToonService toonService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCreateNewToon() throws Exception {

    }

    @Test
    public void testGetToonById() throws Exception {
//        //GIVEN
//        Long id = 1L;
//        Caster caster = new Caster();
//        caster.setId(id);
//        //WHEN
//        when(toonService.getToonById(id)).thenReturn(caster);
//        ResultActions result =
//        //THEN
////        verify(toonService, times(1)).getToonById(id);
//        mockMvc.perform(get("http://localhost:8080/api/toons/1"))
//                .accept(MediaType.APPLICATION_JSON);
    }

    @Test
    public void testGetHealthById() throws Exception {
    }
    
}
