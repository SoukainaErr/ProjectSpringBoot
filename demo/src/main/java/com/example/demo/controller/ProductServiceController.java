package com.example.demo.controller;

import com.example.demo.delegate.ProductServiceDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.crypto.Data;

@RestController
public class ProductServiceController {

    @Autowired
    ProductServiceDelegate productServiceDelegate;

    @RequestMapping(value = "/afficherNomProduit/{id}",method = RequestMethod.GET)
    public String getProductName(@PathVariable int id) {
        return productServiceDelegate.callFindProduct(id);
    }


    @RequestMapping(value = "/Produits/{id}",method = RequestMethod.GET)
    public String getProduct(@PathVariable int id){
        return productServiceDelegate.getProduct(id);
    }

    @RequestMapping(value = "/Produits",method = RequestMethod.GET)
    public String getProducts(){
        return productServiceDelegate.getAllProducts();
    }

    @RequestMapping(value = "/SortingProducts",method = RequestMethod.GET)
    public String getProductListAlphabeticOrder(){
        return productServiceDelegate.getProductListAlphabeticOrder();
    }

    @RequestMapping(value = "/Produits",method = RequestMethod.POST)
    public void createProduct() {
         productServiceDelegate.createProduct();
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public void login() {
        productServiceDelegate.login();
    }

    @RequestMapping(value = "/user",method = RequestMethod.GET)
    public void getUser() {
        productServiceDelegate.getUser();
    }

}
