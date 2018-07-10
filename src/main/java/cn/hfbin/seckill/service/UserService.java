package cn.hfbin.seckill.service;

import cn.hfbin.seckill.entity.User;
import cn.hfbin.seckill.param.LoginParam;
import cn.hfbin.seckill.result.Result;

/**
 * My Blog : www.hfbin.cn
 * github: https://github.com/hfbin
 * Created by: HuangFuBin
 * Date: 2018/7/10
 * Time: 12:00
 * Such description:
 */
public interface UserService {
    Result<User> login(LoginParam loginParam);
}
