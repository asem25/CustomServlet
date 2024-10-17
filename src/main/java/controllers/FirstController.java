package controllers;

import annotations.Controller;
import annotations.GetMapping;
import annotations.RequestMethod;
import models.ResponseMethod;

@Controller
public class FirstController {
    @GetMapping(path = "/api")
    public ResponseMethod getRequest(){
        return new ResponseMethod(RequestMethod.GET.toString(), "Get method from first controller");
    }
}
