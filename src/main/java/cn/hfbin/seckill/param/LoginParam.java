package cn.hfbin.seckill.param;

import cn.hfbin.seckill.validator.IsMobile;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * Created by: HuangFuBin
 * Date: 2018/7/10
 * Time: 10:11
 * Such description:
 */
public class LoginParam {
    @NotNull
    @IsMobile
    private String mobile;

    @NotNull(message="密码不能为空")
    @Length(min=32)
    private String password;

    public String getMobile() {
        return mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    @Override
    public String toString() {
        return "LoginVo [mobile=" + mobile + ", password=" + password + "]";
    }
}
