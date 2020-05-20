package com.lzq.springbootmybatis01.Entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @program: springboot-mybatis01
 * @description:
 * @author: liuzhenqi
 * @create: 2020-05-20 16:07
 **/
public class Goods implements Serializable {
    private Integer id;
    private String goodsName;
    private Integer stock;
    private BigDecimal price;
    public Goods(){}
    public Goods(Integer id, String goodsName, Integer stock, BigDecimal price) {
        this.id = id;
        this.goodsName = goodsName;
        this.stock = stock;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
