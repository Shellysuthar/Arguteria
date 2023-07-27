package com.example.arguteriaBackend.controllerImpl;

import com.example.arguteriaBackend.controller.BillControl;
import com.example.arguteriaBackend.model.Bill;
import com.example.arguteriaBackend.service.BillService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RestController
@RequiredArgsConstructor
public class BillControlImpl implements BillControl {

    @Autowired
    private BillService billService;
    @Override
    public ResponseEntity<String> generateBill(Map<String, Object> requestMap) {
        try{
            return billService.generateBill(requestMap);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<Bill>> getBill() {
        try{
            return billService.getBill();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>() , HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<Bill>> getCompletedBill(){
        try{
            return billService.getCompletedBill();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>() , HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<Bill>> getPendingBill(){
        try{
            return billService.getPendingBill();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>() , HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String > updateStatus(@PathVariable Integer id){
        try{
            return billService.updateStatus(id);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>("Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
