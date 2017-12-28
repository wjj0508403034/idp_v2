package com.huoyun.idp.config.session;

import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@EnableRedisHttpSession(redisNamespace = "idp")
public class HttpSessionConfiguration {

}
