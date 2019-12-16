package cn.zk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class loginController {
    @RequestMapping({"/","/index.html"})
    public String index(){
        return "index";
    }
    @RequestMapping("/main.html")
    public String main(){
        return "dashboard";
    }
    //@RequestMapping(value = "/user/login",method = RequestMethod.POST)
    @PostMapping(value = "/user/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password, HttpSession session, Map<String, Object> map){
        if(username.equals("zz")&&password.equals("123")){
            //登录成功,防止表单重复提交，可以重定向到主页
            session.setAttribute("loginUser",username);
            return "redirect:/main.html";
        }else{
            //登陆失败
            map.put("msg","用户名或密码错误");
            return "index";
        }

    }

}
