package cn.hfbin.seckill.controller;

import cn.hfbin.seckill.entity.User;
import cn.hfbin.seckill.redis.RedisService;
import cn.hfbin.seckill.redis.UserKey;
import cn.hfbin.seckill.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    @Autowired
    RedisService redisService;
    @RequestMapping("index")
    public String idnex(){
        System.out.println("index");
        return "index";
    }
    @RequestMapping("/redis/get")
    @ResponseBody
    public Result<User> redisGet() {
        User  user  = redisService.get(UserKey.getById, ""+1, User.class);
        return Result.success(user);
    }

    @RequestMapping("/redis/set")
    @ResponseBody
    public Result<Boolean> redisSet() {
        User user  = new User();
        user.setId(1);
        user.setName("1111");

        redisService.set(UserKey.getById, ""+2, user);//UserKey:id1
        return Result.success(true);
    }
}
