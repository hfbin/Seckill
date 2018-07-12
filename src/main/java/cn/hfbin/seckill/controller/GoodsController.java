package cn.hfbin.seckill.controller;

import cn.hfbin.seckill.bo.GoodsBo;
import cn.hfbin.seckill.redis.RedisService;
import cn.hfbin.seckill.service.SeckillGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
    SeckillGoodsService seckillGoodsService;

    @RequestMapping("/list")
    public String list(Model model) {
        List<GoodsBo> goodsList = seckillGoodsService.getSeckillGoodsList();
        model.addAttribute("goodsList", goodsList);
        return "goods_list";
    }
    @RequestMapping("/to_detail/{goodsId}")
    public String detail(Model model,
                         @PathVariable("goodsId")long goodsId) {

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

