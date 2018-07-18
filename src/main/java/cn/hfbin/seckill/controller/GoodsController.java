package cn.hfbin.seckill.controller;

import cn.hfbin.seckill.bo.GoodsBo;
import cn.hfbin.seckill.common.Const;
import cn.hfbin.seckill.entity.User;
import cn.hfbin.seckill.redis.GoodsKey;
import cn.hfbin.seckill.redis.RedisService;
import cn.hfbin.seckill.redis.UserKey;
import cn.hfbin.seckill.service.SeckillGoodsService;
import cn.hfbin.seckill.util.CookieUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.spring4.context.SpringWebContext;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by: HuangFuBin
 * Date: 2018/7/11
 * Time: 20:52
 * Such description:
 */

@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    RedisService redisService;
    @Autowired
    SeckillGoodsService seckillGoodsService;


    @Autowired
    ThymeleafViewResolver thymeleafViewResolver;

    @Autowired
    ApplicationContext applicationContext;

    @RequestMapping("/list")
    public String list(Model model, HttpServletRequest request, HttpServletResponse response) {
        List<GoodsBo> goodsList = seckillGoodsService.getSeckillGoodsList();
        model.addAttribute("goodsList", goodsList);
        return "goods_list";
    }
    @RequestMapping("/to_detail/{goodsId}")
    public String detail(Model model,
                         @PathVariable("goodsId")long goodsId ,HttpServletRequest request  ,HttpServletResponse response  ) {

        String loginToken = CookieUtil.readLoginToken(request);
        User user = redisService.get(UserKey.getByName, loginToken, User.class);
        model.addAttribute("user", user);
        GoodsBo goods = seckillGoodsService.getseckillGoodsBoByGoodsId(goodsId);
        if(goods == null){
            return "error/404";
        }else {
            model.addAttribute("goods", goods);
            long startAt = goods.getStartDate().getTime();
            long endAt = goods.getEndDate().getTime();
            long now = System.currentTimeMillis();

            int miaoshaStatus = 0;
            int remainSeconds = 0;
            if(now < startAt ) {//秒杀还没开始，倒计时
                miaoshaStatus = 0;
                remainSeconds = (int)((startAt - now )/1000);
            }else  if(now > endAt){//秒杀已经结束
                miaoshaStatus = 2;
                remainSeconds = -1;
            }else {//秒杀进行中
                miaoshaStatus = 1;
                remainSeconds = 0;
            }
            model.addAttribute("miaoshaStatus", miaoshaStatus);
            model.addAttribute("remainSeconds", remainSeconds);
            return "goods_detail";
        }
    }
}

