package cn.hfbin.seckill.mq;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by: HuangFuBin
 * Date: 2018/6/6
 * Time: 12:48
 * Such description: 更改数据序列化格式
 */
@Configuration
public class AMQPConfig {


    @Bean
    public MessageConverter getMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
