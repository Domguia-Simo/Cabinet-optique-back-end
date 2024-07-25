package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import javax.swing.plaf.synth.SynthEditorPaneUI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.interfaces.ProductInterface;
import com.example.demo.models.Product;
import com.example.demo.repo.ProductRepository;

@Service
public class ProductServiceImp implements ProductInterface {

    @Autowired
    private final ProductRepository productRepository;

    public ProductServiceImp(ProductRepository productRepository){
        this.productRepository = productRepository;
    }


    @Override
    public Long createProduct(Product product){
       Product response = productRepository.save(product);
        System.out.println("The id of the created product : "+response.getId());
        return response.getId();
    }
    
    @Override
    public void updateProduct(Product product){
        productRepository.save(product);
    }
    
    @Override
    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }
    
    @Override
    public List<Product> getProduct(){
        return productRepository.findAll();
    }

    @Override
    public Boolean saveImage(String fileName ,Long id){

        System.out.println(fileName);
        
        Optional<Product> p = productRepository.findById(id);
        System.out.println(p.get().getName());
        
        if(p.isPresent()){
            System.out.println("the products is present");
            Product updatedP = p.get();
            if(updatedP.getImage() != null){
                String oldImages = updatedP.getImage();
                oldImages = oldImages +"*"+fileName;
                updatedP.setImage(oldImages);
                System.out.println("the list of images already existing"+updatedP.getImage());
            }else{
                updatedP.setImage(fileName);
                System.out.println("The list of images is empty");
            }
            productRepository.save(updatedP);
            return true;
        }
        else{
            System.out.println("eRROR saving file");
            return false;
        }

    }
        
}
