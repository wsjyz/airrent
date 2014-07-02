package com.eighth.airrent.domain;

import java.math.BigDecimal;

/**
 * Created by dam on 2014/7/2.
 */
public class VIPCard extends BaseDomain{

    private String cardId;//主键
    private String cardName;//卡名称
    private BigDecimal cardPrice;//年费

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public BigDecimal getCardPrice() {
        return cardPrice;
    }

    public void setCardPrice(BigDecimal cardPrice) {
        this.cardPrice = cardPrice;
    }
}
