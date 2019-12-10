package com.example.demo.to;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * TODO description：文章数据-TO 用于后端服务之间进行数据传输
 *
 * @author bing.li
 * @version v1.0
 */
@Data
@ToString
public class ArticlesTO {

    private String mid;
    /**
     * 标题
     */
    private String title;
    /**
     * 作者
     */
    private String author;
    /**
     * 文章内容
     */
    private String content;

}
