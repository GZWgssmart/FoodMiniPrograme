package com.gzzy.foodManProgram.entity.common;

import java.util.Date;
import java.util.List;

/**
 * @ClassName StoreProductBean
 * @Description TODO
 * @Author wangbin
 * @Date 2018/8/16/016 16:49
 * @Version 1.0
 */
public class StoreProductBean {
    private Store store;

    private List<Product> products;

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
