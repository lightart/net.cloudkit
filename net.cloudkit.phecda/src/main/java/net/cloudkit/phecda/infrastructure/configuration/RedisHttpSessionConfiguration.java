package net.cloudkit.phecda.infrastructure.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * RedisHttpSessionConfiguration
 *
 * @author hongquanli <hongquanli@qq.com>
 * @version 1.0 2015年08月26日 上午11:38:34
 */
@Configuration
@EnableRedisHttpSession
public class RedisHttpSessionConfiguration {

}
