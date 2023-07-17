package com.example.arguteriaBackend.serviceImpl;

import com.example.arguteriaBackend.error.Cafeutils;
import com.example.arguteriaBackend.jwt.JwtFilter;
import com.example.arguteriaBackend.model.Bill;
import com.example.arguteriaBackend.repo.BillRepo;
import com.example.arguteriaBackend.service.BillService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class BillServiceImpl implements BillService {
    @Autowired
    private BillRepo billRepo;

    @Autowired
    private JwtFilter jwtFilter;
    @Override
    public ResponseEntity<String> generateBill(Map<String, Object> requestMap) {
        try{
            String uuid;
            if( this.validateRequestMap(requestMap)){
                   uuid= Cafeutils.getUUID();
                   requestMap.put("uuid", uuid);
                   this.insertBill(requestMap);

               return new ResponseEntity<>("Created",HttpStatus.OK);
            }
            else{
                new ResponseEntity<>("Invalid Data",HttpStatus.BAD_REQUEST);
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<Bill>> getBill() {
        List<Bill> bills = new ArrayList<>();
        try{
            if(jwtFilter.isAdmin()){
                bills= billRepo.findAll();
            }
            else{
                bills= billRepo.findAllByEmail(jwtFilter.getCurrentUser());
            }
            return new ResponseEntity<>(bills, HttpStatus.OK);
        }catch (Exception ex){
            ex.printStackTrace();
            return new ResponseEntity<>(bills, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    private void insertBill(Map<String, Object> requestMap) {
        try{
            Bill bill = new Bill();
            bill.setUuid((String)requestMap.get("uuid"));
            bill.setFirstName((String)requestMap.get("firstName"));
            bill.setLastName((String)requestMap.get("lastName"));
            bill.setEmail((String) requestMap.get("email"));
            bill.setTotalAmount((Number) requestMap.get("totalAmount"));
            bill.setProductDetail((String) requestMap.get("productDetail"));

            billRepo.save(bill);

        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private boolean validateRequestMap(Map<String, Object> requestMap) {
        return requestMap.containsKey("firstName")&&
                requestMap.containsKey("lastName")&&
                requestMap.containsKey("email")&&
                requestMap.containsKey("totalAmount")&&
                requestMap.containsKey("productDetail");
    }
}

