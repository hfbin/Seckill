package cn.hfbin.seckill.service.ipml;

import cn.hfbin.seckill.bo.GoodsBo;
import cn.hfbin.seckill.dao.SeckillOrderMapper;
import cn.hfbin.seckill.entity.OrderInfo;
import cn.hfbin.seckill.entity.SeckillOrder;
import cn.hfbin.seckill.entity.User;
import cn.hfbin.seckill.service.SeckillOrderService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by: HuangFuBin
 * Date: 2018/7/16
 * Time: 16:47
 * Such description:
 */
public class SeckillOrderServiceImpl implements SeckillOrderService {

    @Autowired
    SeckillOrderMapper seckillOrderMapper;
    @Override
    public SeckillOrder getSeckillOrderByUserIdGoodsId(long userId, long goodsId) {
        return seckillOrderMapper.selectByUserIdAndGoodsId(userId , goodsId);
    }

    @Override
    public OrderInfo insert(User user, GoodsBo goodsBo) {
        return null;
    }
}
