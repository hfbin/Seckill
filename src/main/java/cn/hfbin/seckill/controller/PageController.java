package cn.hfbin.seckill.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by: HuangFuBin
 * Date: 2018/7/9
 * Time: 12:36
 * Such description:
 */

@Controller
@RequestMapping("/page")
public class PageController {


    @RequestMapping("login")
    public String loginPage(){

        return "login";
    }
}
