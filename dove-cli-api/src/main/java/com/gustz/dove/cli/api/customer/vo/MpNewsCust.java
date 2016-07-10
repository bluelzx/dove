package com.gustz.dove.cli.api.customer.vo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gustz.dove.cli.api.service.vo.AbstCliBaseVo;

/**
 * 
 * TODO: 组合新闻消息
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ]
 */
public class MpNewsCust extends AbstCliBaseVo {

    private static final long serialVersionUID = 1L;

    /**
     * 多条新闻消息信息列表，默认第一个item为大图
     */
    @JsonProperty("articles")
    private List<MpArticleCust> mpArticles;

    public MpNewsCust() {
        super();
    }

    public MpNewsCust(List<MpArticleCust> mpArticles) {
        this();
        this.mpArticles = mpArticles;
    }

    public List<MpArticleCust> getMpArticles() {
        return mpArticles;
    }

    public void setMpArticles(List<MpArticleCust> mpArticles) {
        this.mpArticles = mpArticles;
    }

}
