
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import servlets.CustomServlet;

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

    @BeforeEach
     void setUp(){
        List<String> strings = List.of("List");
        customServlet = new CustomServlet(strings);
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        stringWriter = new StringWriter();

    }
    @Test
    void testDoGet() throws IOException, ServletException {
        PrintWriter printWriter = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(printWriter);

        customServlet.doGet(request, response);
        printWriter.flush();
        String res = stringWriter.toString().trim();


        assertEquals("List", res);
        verify(response).setContentType("text/plain");
    }
    @Test
    void testDoPost() throws IOException, ServletException {
        PrintWriter printWriter = new PrintWriter(stringWriter);
        when(request.getParameter("param")).thenReturn("testValue");
        when(response.getWriter()).thenReturn(printWriter);


        customServlet.doPost(request, response);

        // Проверяем содержимое ответа
        String result = stringWriter.toString().trim();
        assertEquals("Parameter: testValue", result);
    }
}
