package cn.hfbin.seckill.controller;

import cn.hfbin.seckill.param.LoginParam;
import cn.hfbin.seckill.result.Result;
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

    @RequestMapping("/login")
    @ResponseBody
    public Result<Boolean> doLogin(HttpServletResponse response,@Valid LoginParam loginParam) {
        //登录
        //userService.login(response, loginVo);
        return Result.success(true);
    }
}
