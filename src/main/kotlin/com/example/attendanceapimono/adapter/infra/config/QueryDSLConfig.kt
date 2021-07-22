package com.example.attendanceapimono.adapter.infra.config

import com.querydsl.jpa.impl.JPAQueryFactory
import com.querydsl.jpa.sql.JPASQLQuery
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Configuration
class QueryDSLConfig(
    @PersistenceContext
    private val entityManager: EntityManager
) {

    @Bean
    fun jpaQueryFactory() = JPAQueryFactory(entityManager)
}