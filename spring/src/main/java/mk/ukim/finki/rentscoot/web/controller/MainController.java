package mk.ukim.finki.rentscoot.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;


@Controller
public class MainController {

    @RequestMapping(value = "/About", method = RequestMethod.GET)
    public String about(Model model, HttpServletRequest request){
        model.addAttribute("__requestPath",getRequestPath(request));
        return "index";
    }

    @RequestMapping(value = "/Rent", method = RequestMethod.GET)
    public String rent(Model model, HttpServletRequest request){
        model.addAttribute("__requestPath",getRequestPath(request));
        return "index";
    }

    @RequestMapping(value = "/Contact", method = RequestMethod.GET)
    public String contact(Model model, HttpServletRequest request){
        model.addAttribute("__requestPath",getRequestPath(request));
        return "index";
    }

    private static String getRequestPath(HttpServletRequest request){
        String queryString = request.getQueryString();
        return request.getRequestURI() + (queryString == null ? "" : "?" + queryString);
    }
}
