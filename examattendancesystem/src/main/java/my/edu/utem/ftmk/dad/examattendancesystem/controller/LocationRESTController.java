package my.edu.utem.ftmk.dad.examattendancesystem.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import my.edu.utem.ftmk.dad.examattendancesystem.model.Location;
import my.edu.utem.ftmk.dad.examattendancesystem.repository.LocationRepository;

/**
 * This is the Location REST Controller 
 * 
 * @author wengchuan
 *
 */
@RestController
@RequestMapping("/api/location")
public class LocationRESTController {
	@Autowired
	private LocationRepository locationRepository;
	
	
	
	
		/**
		 * 
		 * list all location
		 * 
		 * @return
		 */
		@GetMapping
		public List<Location> getLocations() {
			return locationRepository.findAll();
		}

		
		
		
		/**
		 *  get specific location
		 * 
		 * @param locationId
		 * @return
		 */
		@GetMapping("{locationId}")
		public Location getLocationeById(@PathVariable long locationId) {
			Location location = locationRepository.findById(locationId).get();
			return location;
		}

		
		
		/**
		 * insert a new location
		 * 
		 * @param location
		 * @return
		 */
		@PostMapping()
		public Location insertLocation(@RequestBody Location location) {
			return locationRepository.save(location);
		}

		
		/**
		 * update location
		 * 
		 * @param location
		 * @return
		 */
		@PutMapping()
		public Location updateLocation(@RequestBody Location location) {
			return locationRepository.save(location);
		}


		/**
		 * 
		 * delete existing location
		 * 
		 * @param locationId
		 * @return
		 */
		@DeleteMapping("{locationId}")
		public ResponseEntity<HttpStatus> deleteLocation(@PathVariable long locationId) {
			locationRepository.deleteById(locationId);
			return new ResponseEntity<>(HttpStatus.OK);
		}
}
