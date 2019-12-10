package com.example.demo.controller;


import com.alibaba.fastjson.JSONObject;
import com.example.demo.server.ArticleServer;
import com.example.demo.to.ArticlesTO;
import io.swagger.annotations.Api;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * TODO description：文章 控制层。
 *
 * @author bing.li
 * @version v1.0
 * @date 2018-07-20 09:42
 */
@RestController
@RequestMapping("/mdb/article")
@Log4j2
@Api(tags = "用于存储文章的控制层。类路径/mdb/article")
public class ArticleController {

    @Autowired
    private ArticleServer articleServer;

    /**
     * Find by id response wrapper.
     *
     * @param id the mid
     * @return the response wrapper
     */
    @RequestMapping(value = "/findById/{id}", method = RequestMethod.GET)
    public ResponseWrapper findById(@NotNull @PathVariable String id) {
        try {
            ArticlesTO articlesTO = articleServer.findById(id);
            String jsonString = JSONObject.toJSONString(articlesTO);
            return ResponseWrapper.success(200, "查询成功", jsonString);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseWrapper.error();
        }
    }

    /**
     * Save response wrapper.
     *
     * @param articlesTO the article to
     * @return the response wrapper
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseWrapper save(@NotNull @RequestBody ArticlesTO articlesTO) {
        try {
            Object id = articleServer.insert(articlesTO);
            return ResponseWrapper.success(id);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseWrapper.error();
        }
    }

    /**
     * Update response wrapper. 修改时ID不能为空。
     *
     * @param articlesTO the articles to
     * @return the response wrapper
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseWrapper update(@NotNull @RequestBody ArticlesTO articlesTO) {
        try {
            Assert.notNull(articlesTO.getMid(), "文章主键不能为空");

            articleServer.update(articlesTO);
            return ResponseWrapper.success();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseWrapper.error(e.getMessage());
        }
    }

    /**
     * Delete response wrapper.
     *
     * @param id the mid
     * @return the response wrapper
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.PUT)
    public ResponseWrapper delete(@NotNull @PathVariable String id) {
        try {
            articleServer.delete(id, false);
            return ResponseWrapper.success();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseWrapper.error();
        }
    }
}
