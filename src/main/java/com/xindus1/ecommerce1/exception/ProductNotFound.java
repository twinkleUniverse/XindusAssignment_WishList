package com.xindus1.ecommerce1.exception;

public class ProductNotFound extends  RuntimeException{
    public ProductNotFound(String msg){
        super(msg);
    }
}
