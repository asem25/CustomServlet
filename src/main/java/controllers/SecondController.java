package controllers;

import annotations.Controller;
import annotations.GetMapping;
import annotations.PostMapping;
import annotations.RequestMethod;
import jakarta.servlet.http.HttpServletRequest;
import models.ResponseMethod;

@Controller
public class SecondController {
    @PostMapping(path = "/api")
    public ResponseMethod postRequest(HttpServletRequest request){
        return new ResponseMethod(RequestMethod.POST.toString(),
                "Post method from second controller with param:"
                + request.getParameter("param"));
    }
}
