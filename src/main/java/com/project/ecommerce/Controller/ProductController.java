package com.project.ecommerce.Controller;

import com.project.ecommerce.Entity.Product;
import com.project.ecommerce.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productservice){
        productService=productservice;
    }

    @GetMapping("")
    public List<Product> getAllProducts(){
        return productService.getAll();
    }

    @GetMapping("/{id}")
    public Product getproductbyid(@PathVariable int id){
        return productService.getProductById(id);
    }

    @PostMapping("")
    public void addproduct(@RequestBody Product product){
        productService.saveProduct(product);
    }

    @DeleteMapping("/{id}")
    public void deleteproduct(@PathVariable int id){
        productService.deleteProductById(id);
    }

    @PutMapping("/{id}")
    public void updateproduct(@PathVariable int id, @RequestBody Product product){
        product.setId(id);
        productService.saveProduct(product);
    }
}

