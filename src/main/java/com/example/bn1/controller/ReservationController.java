
package com.example.bn1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.bn1.entity.FinePayment;
import com.example.bn1.service.ReservationService;

@RestController
public class ReservationController {

    @Autowired
    ReservationService reservationSer;

    @PostMapping("/reservationpost")
    public FinePayment saveData(@RequestBody FinePayment data) {
        return reservationSer.saveData(data);
    }

    @GetMapping("/reservationget")
    public List<FinePayment> getData() {
        return reservationSer.getAllData();
    }

    @GetMapping("/reservationget/{id}")
    public FinePayment getUserData(@PathVariable Long id) {
        return reservationSer.getUserDetails(id);
    }

    @PutMapping("/reservationupdate/{id}")
    public FinePayment updateData(@PathVariable Long id,
            @RequestBody FinePayment data) {
        return reservationSer.updateDatabase(id, data);
    }

    @DeleteMapping("/reservationdelete/{id}")
    public FinePayment getDeleteData(@PathVariable Long id) {
        return reservationSer.getDelete(id);
    }
}