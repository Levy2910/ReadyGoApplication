package com.example.readygo.service;

import com.example.readygo.model.Destination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


public interface IDestinationService {
    List<Destination> getAllDestinations();

    Destination getOneDestination(Long destinationID);

    void addDestination(Destination destination);

    void deleteDestination(Long destinationId);
}
