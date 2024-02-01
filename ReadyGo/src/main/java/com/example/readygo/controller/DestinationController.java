package com.example.readygo.controller;

import com.example.readygo.model.Destination;
import com.example.readygo.service.DestinationService;
import com.example.readygo.service.IDestinationService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class DestinationController {
    private final IDestinationService iDestinationService;

    @CrossOrigin(
            origins = {
                    "http://localhost:3000"
            },
            methods = {
                    RequestMethod.OPTIONS,
                    RequestMethod.GET,
                    RequestMethod.PUT,
                    RequestMethod.DELETE,
                    RequestMethod.POST
            })
    @GetMapping("/destinations")
    public ResponseEntity<List<Destination>> getAllDestinations() {
        try {

            List<Destination> destinations = iDestinationService.getAllDestinations();
            return ResponseEntity.ok(destinations);
        }
        catch(Exception e){
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }
    @GetMapping("/detail/{destinationID}")
    public ResponseEntity<Destination> getOneDestination(@PathVariable Long destinationID){
        try{
            Destination destination = iDestinationService.getOneDestination(destinationID);
            return ResponseEntity.ok(destination);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/addDestination")
    public ResponseEntity<Destination> addDestination(
            @RequestBody Destination destination){
        try{
            iDestinationService.addDestination(destination);
            return ResponseEntity.ok(destination);
        }catch (Exception e){
            System.out.printf( e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/deleteDestination/{destination_id}")
    public ResponseEntity<String> deleteDestination(@PathVariable Long destination_id){
        try {
            iDestinationService.deleteDestination(destination_id);
            return ResponseEntity.ok("yes, deleted it successfully");
        }catch(Exception e){
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("sorry can't delete, something went wrong");
        }
    }

}
