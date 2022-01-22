package com.hs.model;

/**
 * Description:
 *
 * @Auther: Administrator
 * @Data: 2021/11/18 0018 19:35
 */
public class Owner {
    private String name;
    private String cardId;
    private String area;

    @Override
    public String toString() {
        return "Owner{" +
                "name='" + name + '\'' +
                ", cardId='" + cardId + '\'' +
                ", area='" + area + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
