package net.yorksolutions.jsontest;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
public class JsonTestController {

    private JsonTestService jsonTestService;

    public JsonTestController(JsonTestService jsonTestService){
        this.jsonTestService = jsonTestService;
    }

    @GetMapping("/ip")
    public HashMap getIp(HttpServletRequest request){
        return this.jsonTestService.getIp(request);
    }

    @GetMapping("/headers")
    public HashMap getHeaders(@RequestHeader Map<String, String> headerInfo){
        return this.jsonTestService.getHeaders(headerInfo);
    }

    @GetMapping("/date")
    public HashMap getDate(){
        return this.jsonTestService.getDate();
    }

    @GetMapping("/echo/**")
    public HashMap echo(HttpServletRequest request) {
        return this.jsonTestService.echo(request);
    }

    @GetMapping("/md5/{text}")
    public HashMap echoJson(@PathVariable String text){
        return this.jsonTestService.md5(text);
    }

    @GetMapping("/code")
    public String code(HttpServletRequest request){
        return this.jsonTestService.code(request);
    }

    /* @GetMapping("/validate")
    public HashMap jsonValidator(@RequestParam String json){
        return this.jsonTestService.jsonValidator(json);
    } */
}