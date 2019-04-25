package cn.hfbin.seckill.common;

/**
 * Created by: HuangFuBin
 * Date: 2018/7/11
 * Time: 20:27
 * Such description:
 */
public class Const {

    public interface RedisCacheExtime{
        int REDIS_SESSION_EXTIME = 60 * 30;//30分钟
        int GOODS_LIST = 60 * 30 * 24;//1分钟
        int GOODS_ID = 60;//1分钟
        int SECKILL_PATH = 60;//1分钟
        int GOODS_INFO = 60;//1分钟
    }
}
