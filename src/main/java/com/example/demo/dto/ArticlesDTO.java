package com.example.demo.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * TODO description：文章数据展示类-DTO 用于前端请求数据和数据展示。。
 * @author bing.li
 */
@Data
@ToString
public class ArticlesDTO {
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
