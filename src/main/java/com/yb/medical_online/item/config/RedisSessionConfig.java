package com.yb.medical_online.item.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.Session;
import org.springframework.session.SessionRepository;
import org.springframework.session.data.redis.RedisOperationsSessionRepository;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.data.redis.config.annotation.web.http.RedisHttpSessionConfiguration;
import org.springframework.session.web.http.SessionRepositoryFilter;

@Configuration
public class RedisSessionConfig extends RedisHttpSessionConfiguration {

    @Value("${redis.session.timeout}")
    private int sessionTimeOut;

    @Bean
    @Override
    public <S extends Session> SessionRepositoryFilter<? extends Session> springSessionRepositoryFilter(SessionRepository<S> sessionRepository) {
        ((RedisOperationsSessionRepository)sessionRepository).setDefaultMaxInactiveInterval(sessionTimeOut);
        return super.springSessionRepositoryFilter(sessionRepository);
    }
}
