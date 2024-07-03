package com.viaje.viaje;

import com.viaje.viaje.controller.TravelPlansController;
import com.viaje.viaje.controller.UserController;
import com.viaje.viaje.dto.TravelPlansDTO;
import com.viaje.viaje.dto.UserDTO;
import com.viaje.viaje.model.TravelPlans;
import com.viaje.viaje.model.Users;
import com.viaje.viaje.service.TravelPlansService;
import com.viaje.viaje.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpSession;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.*;

class TravelPlansControllerTest {

    @Mock
    private TravelPlansService travelPlansService;

    @Mock
    private UserService userService;

    @InjectMocks
    private TravelPlansController travelPlansController;

    @InjectMocks
    private UserController userController;
    private MockHttpSession session;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        session = new MockHttpSession();
    }
    @Test
    void TestCreatePlan() {
        // Given
        TravelPlansDTO tpDTO = new TravelPlansDTO();
        tpDTO.setNation("USA");
        tpDTO.setTitle("Visit to New York");
        tpDTO.setDetail("Exploring the city");
        tpDTO.setFileName("newyork.jpg");
        tpDTO.setFilePath("/images/newyork.jpg");

        UserDTO userdto =UserDTO.builder()
                .userName("John Doe")
                .email("test1@naver.com")
                .nickname("test1")
                .password("test1")
                .build();
        Users testuser = userService.registerUser(userdto);
        session.setAttribute("user", testuser);


    }
}