package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
//씨발
@Controller
public class HelloController {

    @GetMapping("hello")
    public String Hello(Model model) {
        model.addAttribute("data", "hello!");
        model.addAttribute("asset", "assets!");
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String heloMvc(@RequestParam(value="name") String name, Model model){ // 외부에서 파라미터를 받음(ex. url 같은거).@Requestparam
        //value 안에 있는 값으로 url에서 검색하게 된다. hell-mvc?name=  다음에 오는 값을 넣어줌
        model.addAttribute("name", name); // 리퀘스트 파람으로 받은 데이터를 attributeName으로 하고, 값을 name으로 넘겨줌
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody //http body 부분에 return에 들어가는 값을 직접 넣을 수 있음. html로 안보내고 직접 값을 보내는거임
    public String helloString(@RequestParam("name") String name) {
        return "hello" + name;
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }
    static class Hello { // ststic으로 만들면 클래스 내에서 또 사용 가능
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
