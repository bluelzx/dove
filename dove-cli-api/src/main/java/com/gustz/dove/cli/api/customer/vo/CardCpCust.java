package com.gustz.dove.cli.api.customer.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gustz.dove.cli.api.service.vo.AbstCliBaseVo;

/**
 * 
 * TODO: 卡券消息
 *
 * @author ZHENFENG ZHANG
 * @since [ Aug 3, 2015 ]
 */
public class CardCpCust extends AbstCliBaseVo {

    private static final long serialVersionUID = 1L;

    /**
     * 卡券ID
     */
    @JsonProperty("card_id")
    private String cardId;

    /**
     * 卡券内容
     */
    @JsonProperty("card_ext")
    private String cardExt;

    public CardCpCust() {
        super();
    }

    public CardCpCust(String cardId, String cardExt) {
        this();
        this.cardId = cardId;
        this.cardExt = cardExt;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getCardExt() {
        return cardExt;
    }

    public void setCardExt(String cardExt) {
        this.cardExt = cardExt;
    }

}
