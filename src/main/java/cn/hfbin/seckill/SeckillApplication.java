package cn.hfbin.seckill;

import cn.hfbin.seckill.filter.SessionExpireFilter;
import org.apache.catalina.filters.RemoteIpFilter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@MapperScan("cn.hfbin.seckill.dao")
public class SeckillApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(SeckillApplication.class, args);
	}


}
