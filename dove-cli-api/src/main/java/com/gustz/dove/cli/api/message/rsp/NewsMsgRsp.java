package com.gustz.dove.cli.api.message.rsp;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gustz.dove.cli.api.message.vo.Article;
import com.gustz.dove.cli.api.service.dict.MsgTypeDict;

/**
 * 
 * TODO: 新闻响应报文
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ]
 */
@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class NewsMsgRsp extends MsgBaseRsp<NewsMsgRsp> {

    private static final long serialVersionUID = 1L;

    /**
     * 图文消息个数，限制为10条以内。
     */
    @XmlElement(name = "ArticleCount")
    @JsonProperty("ArticleCount")
    private int articleCount;

    /**
     * 多条图文消息信息，默认第一个item为大图。
     */
    @XmlElement(name = "Articles")
    @JsonProperty("Articles")
    private List<Article> articles;

    public NewsMsgRsp() {
        super();
        super.setMsgType(MsgTypeDict.NEWS);
    }

    public NewsMsgRsp(int articleCount, List<Article> articles) {
        super();
        this.articleCount = articleCount;
        this.articles = articles;
    }

    public int getArticleCount() {
        return articleCount;
    }

    public void setArticleCount(int articleCount) {
        this.articleCount = articleCount;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

}