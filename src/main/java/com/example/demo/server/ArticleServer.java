package com.example.demo.server;


import com.example.demo.entity.ArticlesPO;
import com.example.demo.repository.ArticleRepository;
import com.example.demo.to.ArticlesTO;
import com.example.demo.utils.BeanConversionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

/**
 * TODO description：文章数据处理层
 * BeanUtils: 使用说明 https://blog.csdn.net/wlxtaking/article/details/4691187
 *
 * @author bing.li
 * @version v1.0
 */
@Service
public class ArticleServer {
    @Autowired
    private ArticleRepository articleRepository;


    /**
     * Find by id article vo.
     *
     * @param id the id
     * @return the article vo
     */
    public ArticlesTO findById(String id) throws Exception {
        Optional<ArticlesPO> optional = articleRepository.findById(id);
        ArticlesPO articlesPO = optional.get();
        return BeanConversionUtil.copyProperties(articlesPO, ArticlesTO.class);
    }

    /**
     * Insert string.
     *
     * @param articlesTO the article dto
     * @return the string id
     * @throws Exception the exception
     */
    public String insert(ArticlesTO articlesTO) throws Exception {
        ArticlesPO articlesPO = BeanConversionUtil.copyProperties(articlesTO, ArticlesPO.class);
        articlesPO.setCreateTime(new Date());
        articlesPO.setOmit(0);
        return articleRepository.insert(articlesPO).getId();
    }

    /**
     * Update boolean.
     *
     * @param articlesTO the articles to
     * @return the boolean
     * @throws Exception the exception
     */
    public boolean update(ArticlesTO articlesTO) throws Exception {
        ArticlesPO articlesPO = BeanConversionUtil.copyProperties(articlesTO, ArticlesPO.class);
        articlesPO.setId(articlesTO.getMid());
        articleRepository.save(articlesPO);
        return true;
    }

    /**
     * Delete boolean. 逻辑删除和物理删除
     *
     * @param id        the id
     * @param expurgate the expurgate 是否物理删除 true 物理删除，false 逻辑删除
     * @return the boolean
     * @throws Exception the exception
     */
    public boolean delete(String id, Boolean expurgate) throws Exception {
        Optional<ArticlesPO> optional = articleRepository.findById(id);
        ArticlesPO articlesPO = optional.get();
        if (expurgate) {
            //物理删除，数据找不回来了。
            articleRepository.delete(articlesPO);
        } else {
            articlesPO.setOmit(1);
            articlesPO.setOmitTime(new Date());
            articleRepository.save(articlesPO);
        }
        return true;
    }

}
