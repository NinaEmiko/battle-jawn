package com.battlejawn.Controller;

import com.battlejawn.Controllers.HeroController;
import com.battlejawn.Service.HeroService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(controllers = HeroController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class HeroControllerTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private HeroService heroService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCreateNewHero() throws Exception {

    }

    @Test
    public void testGetHeroById() throws Exception {
//        //GIVEN
//        Long id = 1L;
//        Caster caster = new Caster();
//        caster.setId(id);
//        //WHEN
//        when(heroService.getHeroById(id)).thenReturn(caster);
//        ResultActions result = mvc.perform(get("http://localhost:8080/api/hero/1").accept(MediaType.APPLICATION_JSON)
//                );
//        //THEN
//        verify(heroService, times(1)).getHeroById(id);
//        result.andExpectAll(
//                status().isOk(),
//                content().contentType(MediaType.APPLICATION_JSON),
//                content().json(objectMapper.writeValueAsString(heroService))
//        );
    }

    @Test
    public void testGetHealthById() throws Exception {
    }
    
}
