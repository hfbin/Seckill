package cn.hfbin.seckill.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * My Blog : www.hfbin.cn
 * github: https://github.com/hfbin
 * Created by: HuangFuBin
 * Date: 2018/4/26
 * Time: 20:15
 * Such description:
 */
@Controller
@RequestMapping("/test/")
public class TestController {
    @RequestMapping("index")
    public String idnex(){
        System.out.println("index");
        return "index";
    }
}
