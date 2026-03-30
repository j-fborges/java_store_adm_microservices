package br.com.j_fborges.online.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "br.com.j_fborges.online.repository")
public class MongoConfig {

}
