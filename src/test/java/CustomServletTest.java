
import annotations.RequestMethod;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.ResponseMethod;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import servlets.CustomServlet;
import utilsfortest.JsonReader;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;


public class CustomServletTest {
    private CustomServlet customServlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private StringWriter stringWriter;
    private ObjectMapper objectMapper;
    private JsonReader jsonReader;

    @BeforeEach
     void setUp(){
        customServlet = new CustomServlet();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        stringWriter = new StringWriter();
        objectMapper = new ObjectMapper();
        jsonReader = new JsonReader();
    }
    @Test
    void should_return_phrase_DoGet() throws IOException, ServletException {
        PrintWriter printWriter = new PrintWriter(stringWriter);
        when(request.getRequestURI()).thenReturn("/api");
        when(response.getWriter()).thenReturn(printWriter);

        customServlet.doGet(request, response);
        printWriter.flush();
        String res = stringWriter.toString().trim();


        ResponseMethod responseMethod = jsonReader.mapJsonToResponseMethod(stringWriter.toString(), objectMapper);
        assertEquals("GET : Get method from first controller",
                responseMethod.getReq_type() + " : " + responseMethod.getMessage());

    }
    @Test
    void should_return_value_DoPost() throws IOException, ServletException {
        PrintWriter printWriter = new PrintWriter(stringWriter);
        when(request.getParameter("param")).thenReturn("testValue");
        when(response.getWriter()).thenReturn(printWriter);
        when(request.getRequestURI()).thenReturn("/api");

        customServlet.doPost(request, response);

        // Проверяем содержимое ответа
        ResponseMethod responseMethod = jsonReader.mapJsonToResponseMethod(stringWriter.toString(), objectMapper);
        assertEquals("POST : Post method from second controller with param:testValue",
                responseMethod.getReq_type() + " : " + responseMethod.getMessage());
    }
}
