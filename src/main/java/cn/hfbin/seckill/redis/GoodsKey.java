package cn.hfbin.seckill.redis;

public class GoodsKey extends BasePrefix{

	private GoodsKey(String prefix) {
		super(prefix);
	}
	public static GoodsKey getGoodsList = new GoodsKey("gl");
	public static GoodsKey getGoodsDetail = new GoodsKey("gd");
	public static GoodsKey getSeckillGoodsStock= new GoodsKey( "gs");
}
