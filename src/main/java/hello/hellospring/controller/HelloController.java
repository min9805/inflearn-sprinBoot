package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model){
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody //ViewResolve로 넘어가지 않고 바로 전달
    public String helloString(@RequestParam("name") String name){
        return "hello " + name; //html 코드가 내려가는 것이 아니라 문자 그대로 전달된다.
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello; //hello 객체 자체가 넘어간다. localhost:8080/hello-api?name=spring ...
        //JSON -> {"name":"spring"} 형식 전달.
        //문자가 아니라 객체이기 때문에 HttpMessageConverter를 통해 조건에 따라 바뀜
    }

    static class Hello{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
