package com.example.demo.repository;

import com.example.demo.entity.ArticlesPO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * TODO description：数据查询层
 *
 * @author bing.li
 * @version v1.0
 */
@Repository
public interface ArticleRepository extends MongoRepository<ArticlesPO, String> {

}
