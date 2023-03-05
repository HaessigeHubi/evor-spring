package com.evor.evor.controller;

import com.evor.evor.entity.Location;
import com.evor.evor.repository.LocationRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LocationController {
    private LocationRepository locationRepository;

    public LocationController(LocationRepository locationRepository) { this.locationRepository= locationRepository;}

    @GetMapping("/locations")
    List<Location> all(){ return locationRepository.findAll();}

    @GetMapping("/locations/{id}")
    Location one(@PathVariable Long id) throws Exception {
        return locationRepository.findById(id)
                .orElseThrow(() -> new Exception());
    }

    @PostMapping("/locations")
    Location newEvent(@RequestBody Location newEvent) {return locationRepository.save(newEvent);}

    @DeleteMapping("locations")
    void deleteLocation(@RequestBody Location deleteLocation) {locationRepository.delete(deleteLocation);}
}
