package com.example.arguteriaBackend.serviceImpl;

import com.example.arguteriaBackend.jwt.JwtFilter;
import com.example.arguteriaBackend.model.Product;
import com.example.arguteriaBackend.repo.ProductRepo;
import com.example.arguteriaBackend.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepo productRepo;
    @Autowired
    JwtFilter jwtFilter;
    @Override
    public ResponseEntity<String> addProduct(Map<String, String> requestMap) {
        try{
            if(jwtFilter.isAdmin()){
                if(this.validateProductMap(requestMap)){
                    productRepo.save(this.getProductFromMap(requestMap));
                    return new ResponseEntity<>("New Product was added successfully",HttpStatus.OK);
                }
                return new ResponseEntity<>("Invalid Data",HttpStatus.BAD_REQUEST);
            }
            else return new ResponseEntity<>("Unauthorized Access",HttpStatus.UNAUTHORIZED);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>("Something went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<Product>> getAllProduct() {
        try{
            return new ResponseEntity<>(productRepo.findAll(),HttpStatus.OK);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> updateProduct(Map<String, String> requestMap) {
        try{
            System.out.println( "Inside update");
            if(jwtFilter.isAdmin()){
                if(this.validateProductMap(requestMap)){
                    Optional<Product> product=productRepo.findById(Integer.parseInt(requestMap.get("id")));
                    if(product.isPresent()){
                        product.get().setName(requestMap.get("name"));
                        product.get().setDescription(requestMap.get("description"));
                        product.get().setPrice(Integer.parseInt(requestMap.get("price")));
                        System.out.println( "Inside update"+ product.get());
                        productRepo.save(product.get());
                        return new ResponseEntity<>("Product Updated Successfully",HttpStatus.OK) ;

                    }else return new ResponseEntity<>("Product with id " + requestMap.get("id") + " does not exists", HttpStatus.NOT_FOUND);
                }else return new ResponseEntity<>("Data is Invalid",HttpStatus.BAD_REQUEST);
            }else return new ResponseEntity<>("Unauthorized Access",HttpStatus.UNAUTHORIZED);

        }catch(Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>("Something went wrong in updating logic",HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> deleteProduct(Integer id) {
        try{
            if(jwtFilter.isAdmin()){
                Optional<Product> product=productRepo.findById(id);
                if(product.isPresent()){
                    productRepo.deleteById(id);
                    return new ResponseEntity<>("Product was deleted successfully",HttpStatus.OK);
                }else{
                    return new ResponseEntity<>("Product with id:"+id+"does not exist",HttpStatus.NOT_FOUND);
                }
            }else{
                return new ResponseEntity<>("You are not authorized for this action",HttpStatus.UNAUTHORIZED);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>("Something went wrong due to server",HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private Product getProductFromMap(Map<String, String> requestMap) {
        Product product=new Product();
        product.setName(requestMap.get("name"));
        product.setDescription(requestMap.get("description"));
        product.setPrice(Integer.parseInt(requestMap.get("price")));
        return product;

    }

    private boolean validateProductMap(Map<String,String> requestMap){
        if(requestMap.containsKey("name") && requestMap.containsKey("description") && requestMap.containsKey("price")){
            return true;
        }
        return false;
    }
}
