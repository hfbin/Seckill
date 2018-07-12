package cn.hfbin.seckill;

import cn.hfbin.seckill.bo.GoodsBo;
import cn.hfbin.seckill.dao.GoodsMapper;
import cn.hfbin.seckill.entity.Goods;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class SeckillApplicationTests {

	@Autowired
	DataSource dataSource;

	@Autowired
	GoodsMapper goodsMapper;

	@Test
	public void contextLoads() throws SQLException {
		//org.apache.tomcat.jdbc.pool.DataSource
		System.out.println(dataSource.getClass());
		Connection connection = dataSource.getConnection();
		System.out.println(connection);
		connection.close();

	}

	@Test
	public void test01(){
		List<GoodsBo> goodsBos = goodsMapper.selectAllGoodes();

		for (GoodsBo goodsBo : goodsBos){
			log.info(goodsBo+"");
		}
	}
}
