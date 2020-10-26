package com.example.demo.delegate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.xml.crypto.Data;

@Service
public class ProductServiceDelegate {

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "callProductFallBack")
    public String callFindProduct(int id) {
        String resp = restTemplate.exchange("http://localhost:9090/afficherNomProduit/{id}"
                , HttpMethod.GET, null, new ParameterizedTypeReference<String>() {
                }, id).getBody();
        return resp;
    }

    //fall back method
    public String callProductFallBack(int id) {
        System.out.println("Fallback error. Reason: " + id);
        return "Unavailable service!";
    }

    //Method pour retourner un produit
    @HystrixCommand(fallbackMethod = "getProductFallBack")
    public String getProduct(int id) {
        String resp = restTemplate.exchange("http://localhost:9090/Produits/{id}"
                , HttpMethod.GET, null, new ParameterizedTypeReference<String>() {
                }, id).getBody();
        return resp;
    }

    //fall back method
    public String getProductFallBack(int id) {
        System.out.println("Fallback error. Reason: " + id);
        return "Unavailable service!";
    }




    //Method pour retourner la liste des produits
    @HystrixCommand(fallbackMethod = "getAllProductsFallBack")
    public String getAllProducts() {
        String resp = restTemplate.exchange("http://localhost:9090/Produits"
                , HttpMethod.GET, null, new ParameterizedTypeReference<String>() {
                }).getBody();
        return resp;
    }

    //fall back method
    public String getAllProductsFallBack() {
        System.out.println("Fallback error" );
        return "Unavailable service!";
    }

    @HystrixCommand(fallbackMethod = "getProductListAlphabeticOrderFallBack")
    public String getProductListAlphabeticOrder() {
        String resp = restTemplate.exchange("http://localhost:9090/SortingProducts"
                , HttpMethod.GET, null, new ParameterizedTypeReference<String>() {
                }).getBody();
        return resp;
    }

    @HystrixCommand(fallbackMethod = "getProductListAlphabeticOrderFallBack")
    public String getUser() {
        String resp = restTemplate.exchange("http://localhost:9090/user"
                , HttpMethod.GET, null, new ParameterizedTypeReference<String>() {
                }).getBody();
        return resp;
    }

    //fall back method
    public String getProductListAlphabeticOrderFallBack() {
        System.out.println("Fallback error" );
        return "Unavailable service!";
    }


    //Method pour creer un produit
    @HystrixCommand(fallbackMethod = "createProductFallBack")
    public void createProduct() {
        String resp = restTemplate.exchange("http://localhost:9090/Produits"
                , HttpMethod.POST, null, new ParameterizedTypeReference<String>() {
                }).getBody();

    }

    @HystrixCommand(fallbackMethod = "createProductFallBack")
    public void login() {
        String resp = restTemplate.exchange("http://localhost:9090/login"
                , HttpMethod.POST, null, new ParameterizedTypeReference<String>() {
                }).getBody();

    }

    //fall back method
    public void createProductFallBack() {
        System.out.println("Fallback error. Reason: " );
    }


    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
