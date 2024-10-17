package servlets;

import annotations.GetMapping;
import annotations.PostMapping;
import annotations.RequestMethod;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.awt.desktop.AppForegroundListener;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


public class CustomServlet extends HttpServlet {
    private ApplicationContext applicationContext;
    private ObjectMapper objectMapper = new ObjectMapper();

    public CustomServlet() {
        applicationContext = new ApplicationContext();
    }


    private List<String> strings = new ArrayList<>();
    public CustomServlet(List<String> strings) {
        this.strings = strings;
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            handleRequest(req, resp, RequestMethod.GET);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            handleRequest(req, resp, RequestMethod.POST);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
    private void handleRequest(HttpServletRequest request, HttpServletResponse response, RequestMethod requestMethod) throws InstantiationException, IllegalAccessException {
        String path = request.getRequestURI();
        Method method = applicationContext.getControllerMethod(path, requestMethod);

        if (Objects.nonNull(method)){
            try {
                Class<?> controllerClass = method.getDeclaringClass();
                Constructor<?> constructorController = controllerClass.getDeclaredConstructor();

                Object controller = constructorController.newInstance();
                Object res = null;
                if (method.isAnnotationPresent(GetMapping.class))
                    res = method.invoke(controller);
                else if (method.isAnnotationPresent(PostMapping.class)) {
                    res = method.invoke(controller, request);
                }
                if (res != null) {
                    response.setContentType("application/json");

                    String jsonResponse = objectMapper.writeValueAsString(res);

                    response.getWriter().write(jsonResponse);
                }
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            try {
                response.getWriter().write("Not found");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
