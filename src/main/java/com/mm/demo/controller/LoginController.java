package com.mm.demo.controller;

import com.mm.demo.entity.User;
import com.mm.demo.service.LoginService;
import com.mm.until.VerifyUtil;
import lombok.extern.log4j.Log4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

@Controller
public class LoginController {
    @Autowired
    private LoginService loginService;
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @GetMapping("/verifyCode")
    public void getCode(HttpServletResponse response) throws IOException {
        Map<String,Object> map = VerifyUtil.createImage();
        String code = VerifyUtil.code;
        logger.info("系统生成的验证码为：{}",code);
        System.out.println("系统生成的验证码为："+code);

        //将图片输出给浏览器
        BufferedImage image = (BufferedImage) map.get("image");
        response.setContentType("image/png");
        OutputStream os = response.getOutputStream();
        ImageIO.write(image, "png", os);
    }

    @PostMapping("/login")
    public String login(String name , String password, String code, Model model){
        System.out.println(name+password);//获取输入的值，用于开发检测
        User loginUser=null;
        System.out.println("系统生成的验证码为："+VerifyUtil.code);
        System.out.println("前端传过来的验证码为："+code);
        if (code.equals(VerifyUtil.code)){
            loginUser = loginService.login(name, password);
        }else{
            return "验证码不正确";
        }
        if (loginUser!=null){
            System.out.println(loginUser.getName());
            model.addAttribute("name",loginUser.getName());
        }else{
            System.out.println("账号密码不对，请重新输入");
            return "index";
        }
        return "ucenter/ucenter";

    }
    @GetMapping("/register")
    public String register(){
        System.out.println("进来注册页面》》》");
        return "ucenter/addu";
    }

    /**
     * 添加用户
     * @param name
     * @param password
     * @param code
     * @return
     */
    @PostMapping("/addu")
    public String addu(@RequestParam("name") String name,@RequestParam("password") String password,String code){
        String uName=name;
        String uPassword=password;
        User loginUser=null;
        System.out.println("用户名:"+uName+"用户密码："+uPassword);
        User user =new User();
        user.setName(uName);
        user.setPassword(uPassword);
        if (code.equals(VerifyUtil.code)){
            loginUser = loginService.login(name, password);
            if (loginUser!=null){
                return "账号已存在，请重新填写！";
            }else {
                loginService.addUser(user);
            }
        }else{
            return "验证码不正确";
        }
        return "ucenter/addu";
    }

    /**
     * 根据用户名来更查询是否存在此账号
     * @param name
     * @param model
     * @return
     */
    @PostMapping("/udtuser")
    public String udtuser(@RequestParam("uname") String name, Model model){
        User loginUser=null;
        System.out.println("根据姓名查询的名称："+name);
        loginUser = loginService.getUserByName(name);
        System.out.println("根据姓名查询的名称："+loginUser.getName());
        if (loginUser!=null){
            model.addAttribute("name",loginUser.getName());
            model.addAttribute("id",loginUser.getId());
        }else {
            model.addAttribute("name","没有此账号");
        }
        System.out.println("进来修改密码");
        return "ucenter/udt";
    }

    /**
     * 修改当前用户密码
     * @param name
     * @param password
     * @param code
     * @param id
     * @return
     */
    @PostMapping("/updateUser")
    public String updateUser(String name, String password,String code,int id){
        User loginUser=null;
        User user =new User();
        user.setName(name);
        user.setId(id);
        user.setPassword(password);
        if (code.equals(VerifyUtil.code)){
            loginService.updateUser(user);
        }else{
            return "验证码不正确";
        }
        return "index";
    }

    @GetMapping("/index")
    public String index(){
        System.out.println("进来首页index");
        return "index";
    }

}
