package nl.omoda.backend;

import com.fasterxml.jackson.databind.ObjectMapper;

import nl.omoda.backend.controllers.ChangeCalculatorController;
import nl.omoda.model.ChangeRequest;
import nl.omoda.model.ChangeResponse;
import nl.omoda.service.ChangeCalculatorService;
import nl.omoda.utils.Valutas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ChangeCalculatorController.class)
class ChangeCalculatorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ChangeCalculatorService changeCalculatorService;

    @Autowired
    private ObjectMapper objectMapper;
    
    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach()
    public void setup()
    {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
    
    @Test
    void calculateChange_ValidRequest_With_Euro() throws Exception {
    	        
        // Given
        ChangeRequest request = new ChangeRequest();
        request.setValuta(Valutas.euro.name());
        request.setTotalAmount(228.87);
        request.setAmountPaid(250);

        Map<String, Integer> denominations = new HashMap<>();
        denominations.put("€20", 1);
        denominations.put("€1", 1);
        denominations.put("€0.10", 1);
        denominations.put("€0.05", 1);
                
        ChangeResponse mockResponse = new ChangeResponse(100, denominations, 200, "");

        when(changeCalculatorService.calculateChange(any(ChangeRequest.class)))
            .thenReturn(mockResponse);

        // When & Then
        mockMvc.perform(post("/api/calculate-change")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalChange").value(100))
                .andExpect(jsonPath("$.denominations.€20").value(1))
                .andExpect(jsonPath("$.denominations.['€0.05']").value(1)) 
                .andExpect(jsonPath("$.denominations.€1").value(1))
                .andExpect(jsonPath("$.denominations.['€0.10']").value(1))                               
                .andExpect(jsonPath("$.status").value(200))
        		.andExpect(jsonPath("$.text").value(""));
    }
    
    @Test
    void calculateChange_ValidRequest_With_Dollar() throws Exception {
    	        
        // Given
        ChangeRequest request = new ChangeRequest();
        request.setValuta(Valutas.dollar.name());
        request.setTotalAmount(228.87);
        request.setAmountPaid(250);

        Map<String, Integer> denominations = new HashMap<>();
        denominations.put("€20", 1);
        denominations.put("€1", 1);
        denominations.put("€0.10", 1);
        denominations.put("€0.05", 1);
                
        ChangeResponse mockResponse = new ChangeResponse(100, denominations, 200, "");

        when(changeCalculatorService.calculateChange(any(ChangeRequest.class)))
            .thenReturn(mockResponse);

        // When & Then
        mockMvc.perform(post("/api/calculate-change")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalChange").value(100))
                .andExpect(jsonPath("$.denominations.€20").value(1))
                .andExpect(jsonPath("$.denominations.['€0.05']").value(1)) 
                .andExpect(jsonPath("$.denominations.€1").value(1))
                .andExpect(jsonPath("$.denominations.['€0.10']").value(1))                               
                .andExpect(jsonPath("$.status").value(200))
        		.andExpect(jsonPath("$.text").value(""));
    }   
}