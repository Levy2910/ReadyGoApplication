package com.example.readygo.service;

import com.example.readygo.model.Destination;
import com.example.readygo.repository.DestinationRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DestinationService implements IDestinationService{

    private final DestinationRepository destinationRepository;
    @Override
    public List<Destination> getAllDestinations() {
        return destinationRepository.findAll();
    }

    @Override
    public Destination getOneDestination(Long destinationID) {
        return destinationRepository.findById(destinationID)
                .orElseThrow(() -> new EntityNotFoundException("Destination not found with ID: " + destinationID));
    }

    @Override
    public void addDestination(Destination destination) {
        try{
            destinationRepository.save(destination);
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    @Override
    public void deleteDestination(Long destinationId) {
        try{
            destinationRepository.deleteById(destinationId);
            System.out.printf("deleted");
        }
        catch(Exception e){
            System.out.printf(e.getMessage());
        }
    }

}
