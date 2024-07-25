package com.example.demo.controllers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.models.Product;
import com.example.demo.services.ProductServiceImp;

@CrossOrigin(origins ="*")
@RestController
@RequestMapping("/product")
public class ProductController {
    
    @Autowired
    private ProductServiceImp productService;

    @GetMapping("/get-products")
    public ResponseEntity<Object> getProducts(){
        return ResponseEntity.status(200).body(productService.getProduct());
    }

    @PostMapping("/create-product")
    public ResponseEntity<Object> createProduct(@RequestBody Product product){
        System.out.println("The product name "+product.getName());
        Long newId = productService.createProduct(product);
        return ResponseEntity.status(200).body(Map.of("product_id", newId ,"msg" ,"product created successfully"));

    }

    @DeleteMapping("/delete-product/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable("id") Long id){
        productService.deleteProduct(id);
        return ResponseEntity.status(200).body("Product deleted correctly");
    }

    @PutMapping("/update-product")
    public ResponseEntity<Object> updateProduct(@RequestBody Product product){
        productService.updateProduct(product);
        return ResponseEntity.status(200).body("Product updated succesully");
    }

    @PostMapping("/upload-product-image/{id}")
    public ResponseEntity<Object> uploadProductImage(@PathVariable("id") Long id ,@RequestParam("image") List<MultipartFile> files) throws IllegalStateException, IOException{
        List<String> images =  new ArrayList<String>();
        String dir = System.getProperty("user.dir");

        if(!files.isEmpty()){
            for(MultipartFile file:files){
                if(!file.isEmpty()){
                    images.add(file.getOriginalFilename());
                }else{
                    return ResponseEntity.status(400).body(Map.of("error" ,"Please select a file"));
                }
            }
            for(MultipartFile file:files){
                file.transferTo(new File(dir+"/src/main/resources/static" ,file.getOriginalFilename()));
                Boolean test = productService.saveImage(file.getOriginalFilename(), id);  
            }
        }else{
            return ResponseEntity.status(400).body(Map.of("error" ,"Please select a file"));
            
        }
        return ResponseEntity.status(200).body(Map.of("msg" ,"file save correctly"));

    }

}
