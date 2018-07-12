package cn.hfbin.seckill.bo;

import cn.hfbin.seckill.entity.Goods;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class GoodsBo{

	private BigDecimal seckillPrice;

	private Integer stockCount;

	private Date startDate;

	private Date endDate;

	private Long id;

	private String goodsName;

	private String goodsTitle;

	private String goodsImg;

	private BigDecimal goodsPrice;

	private Integer goodsStock;

	private Date createDate;

	private Date updateDate;

	private String goodsDetail;

}
