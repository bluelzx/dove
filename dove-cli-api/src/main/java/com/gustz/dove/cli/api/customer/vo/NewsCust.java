package com.gustz.dove.cli.api.customer.vo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gustz.dove.cli.api.service.vo.AbstCliBaseVo;

/**
 * 
 * TODO: 新闻消息
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ]
 */
public class NewsCust extends AbstCliBaseVo {

    private static final long serialVersionUID = 1L;

    /**
     * 多条新闻消息信息列表，默认第一个item为大图
     */
    @JsonProperty("articles")
    private List<ArticleCust> articles;

    public NewsCust() {
        super();
    }

    public NewsCust(List<ArticleCust> articles) {
        this();
        this.articles = articles;
    }

    public List<ArticleCust> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticleCust> articles) {
        this.articles = articles;
    }

}
