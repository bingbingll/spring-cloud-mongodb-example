package com.example.demo.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

/**
 * TODO description：文章实体类
 *
 * @author bing.li
 * @version v1.0
 */
@Data
@ToString
public class ArticlesPO implements Serializable {
    @Id
    public String id;
    /**
     * 标题
     */
    public String title;
    /**
     * 创建时间
     */
    public Date createTime;
    /**
     * 作者
     */
    public String author;
    /**
     * 是否省略或删除 0 不忽略 1 是忽略
     */
    public Integer omit = 0;
    /**
     * 省略或删除时间
     */
    public Date omitTime;
    /**
     * 文章内容
     */
    public String content;

}
