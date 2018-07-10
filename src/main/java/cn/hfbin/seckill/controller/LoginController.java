package cn.hfbin.seckill.controller;

import cn.hfbin.seckill.entity.User;
import cn.hfbin.seckill.exception.HfbinException;
import cn.hfbin.seckill.param.LoginParam;
import cn.hfbin.seckill.result.CodeMsg;
import cn.hfbin.seckill.result.Result;
import cn.hfbin.seckill.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * Created by: HuangFuBin
 * Date: 2018/7/9
 * Time: 12:37
 * Such description:
 */
@Controller
@RequestMapping("/user")
public class LoginController {

    @Autowired
    UserService userService;
    @RequestMapping("/login")
    @ResponseBody
    public Result<User> doLogin(HttpServletResponse response,@Valid LoginParam loginParam) {
        return userService.login(loginParam);
    }
}
