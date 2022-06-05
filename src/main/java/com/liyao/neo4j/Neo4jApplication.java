package com.liyao.neo4j;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.liyao.neo4j.mapper")
public class Neo4jApplication {

    public static void main(String[] args) {
        SpringApplication.run(Neo4jApplication.class, args);
    }

}
