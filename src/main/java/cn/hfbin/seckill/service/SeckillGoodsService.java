package cn.hfbin.seckill.service;

import cn.hfbin.seckill.bo.GoodsBo;

import java.util.List;

/**
 * My Blog : www.hfbin.cn
 * github: https://github.com/hfbin
 * Created by: HuangFuBin
 * Date: 2018/7/12
 * Time: 19:46
 * Such description:
 */
public interface SeckillGoodsService {

    List<GoodsBo> getSeckillGoodsList();

    GoodsBo getseckillGoodsBoByGoodsId(long goodsId);

    int reduceStock(long goodsId);
}
