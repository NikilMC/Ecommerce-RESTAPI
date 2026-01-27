package com.project.ecommerce.Service;

import com.project.ecommerce.Entity.Product;
import com.project.ecommerce.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository Productrepository){
        productRepository = Productrepository;
    }

    public List<Product> getAll(){
        return productRepository.findAll();
    }

    public Product getProductById(int id){
        Optional<Product> result = productRepository.findById(id);
        Product product = null;
        if(result.isPresent()){
            product = result.get();
        }
        else{
            throw new RuntimeException("Did Not Find Product of id : "+id);
        }
        return product;
    }

    public void saveProduct(Product product){
        productRepository.save(product);
    }

    public void deleteProductById(int id){
        productRepository.deleteById(id);
    }
}