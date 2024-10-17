package servlets;

import annotations.*;
import org.reflections.Reflections;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class ApplicationContext {
    private final Map<String, Map<RequestMethod, Method>> routeMap = new ConcurrentHashMap<>();

    public ApplicationContext() {
        Reflections reflections = new Reflections("controllers");
        final var controllers = reflections.getTypesAnnotatedWith(Controller.class);
        for (Class<?> controller : controllers) {
            for (Method method: Arrays.stream(controller.getMethods()).toList()){
                if (method.isAnnotationPresent(PostMapping.class)){
                    PostMapping mapping = method.getAnnotation(PostMapping.class);
                    routeMap
                            .computeIfAbsent(mapping.path(), k -> new HashMap<>())
                            .put(RequestMethod.POST, method);
                } else if (method.isAnnotationPresent(GetMapping.class)){
                    GetMapping mapping = method.getAnnotation(GetMapping.class);
                    routeMap
                            .computeIfAbsent(mapping.path(), k -> new HashMap<>())
                            .put(RequestMethod.GET, method);
                }

            }
        }
    }

    public Method getControllerMethod(String path, RequestMethod method) {
        return routeMap.getOrDefault(path, Collections.emptyMap()).get(method);
    }
}
