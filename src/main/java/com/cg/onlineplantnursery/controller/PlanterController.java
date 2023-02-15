package com.cg.onlineplantnursery.controller;

import java.util.ArrayList;


import java.util.List;

import javax.persistence.EntityNotFoundException;

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

import com.cg.onlineplantnursery.dto.PlanterAdminResponseDTO;
import com.cg.onlineplantnursery.dto.PlanterDTO;
import com.cg.onlineplantnursery.exceptions.PlanterIdNotFoundException;
import com.cg.onlineplantnursery.entity.Planter;
import com.cg.onlineplantnursery.service.IPlanterService;
import com.cg.onlineplantnursery.util.PlanterDTOConvertor;

@RestController
@RequestMapping("/planter")
public class PlanterController {

	@Autowired
	IPlanterService planterService;

	@Autowired
	PlanterDTOConvertor planterDTOConvertor;

	@PostMapping("/add")
	public ResponseEntity<Planter> addPlanter(@RequestBody Planter Planter) {

		Planter newPlanter = planterService.addPlanter(Planter);
		PlanterAdminResponseDTO responseDTO = planterDTOConvertor.getPlanterAdminResponseDTO(newPlanter);

		return new ResponseEntity<Planter>(Planter, HttpStatus.OK);
	}

	@PutMapping("/updatePlanter/{planterId}")

	public String updatedPlanter(@PathVariable int planterId) throws PlanterIdNotFoundException {
		Planter updatedPlanter = planterService.viewPlanter(planterId);

		return updatedPlanter.toString();

	}

	@DeleteMapping("/planter/delete/{pID}")
	public ResponseEntity<Planter> deletePlanter(@PathVariable Integer pID) throws PlanterIdNotFoundException {
		if (pID == null) {
			throw new PlanterIdNotFoundException("Planter Id Not Found");
		} else {
			Planter deletePlanter = planterService.deletePlanter(pID);

			return new ResponseEntity<Planter>(deletePlanter, HttpStatus.OK);
		}
	}

	@GetMapping("/planter/view/{pID}")
	public ResponseEntity<Planter> viewPlanter(@PathVariable Integer pID) throws PlanterIdNotFoundException {
		// Planter viewPlanter = planterService.viewPlanter(pID);
		if (pID == null) {
			throw new PlanterIdNotFoundException("Planter Id Not Found");
		} else {

			Planter viewPlanter = planterService.viewPlanter(pID);

			return new ResponseEntity<Planter>(viewPlanter, HttpStatus.OK);
		}

	}

//	@GetMapping("/planter/planterCapacity/{pID}")
//	public ResponseEntity<Planter>getPlanterByPlanterCapacity(@PathVariable int planterCapacity)throws PlanterIdNotFoundException {
//		if(planterCapacity == null) {
//			throw new PlanterIdNotFoundException("Planter Id Not Found");
//		}
//		else {
//			Planter viewPlanter = planterService.getPlanterByPlanterCapacity(planterCapacity);
//			
//			return new ResponseEntity<Planter>(viewPlanter, HttpStatus.OK);
//		}

//	}

	@GetMapping("/planters")
	public ResponseEntity<List<PlanterDTO>> viewAllPlanters() throws PlanterIdNotFoundException {
		List<Planter> allPlanters = planterService.viewAllPlanters();
		List<PlanterDTO> allPlanterDTO = new ArrayList<>();
		for (Planter planter : allPlanters) {
			allPlanterDTO.add(planterDTOConvertor.getPlanterDTO(planter));
		}
		return new ResponseEntity<List<PlanterDTO>>(allPlanterDTO, HttpStatus.OK);

	}

//	@GetMapping("planter/planterId/{planterId}")
//	public ResponseEntity<List<PlanterDTO>> viewPlanter( @PathVariable int planterId) throws EntityNotFoundException {
//		List<Planter> allPlantersInDB = planterService.viewPlanter(planterId);
//		if(allPlantersInDB.isEmpty()) 
//		{
//			throw new EntityNotFoundException("No plant exists with this key" + planterId);
//			
//		}
//		else {
//			List<PlanterDTO> dtoList = new ArrayList<>();
//			
//			for (Planter plant : allPlantersInDB) {
//				PlanterDTO dtoObj = planterService.getPlanterDTO(planterId);
//				dtoList.add(dtoObj);
//				}
//			return new ResponseEntity<List<PlanterDTO>>(dtoList,HttpStatus.OK);
//			}
//		}

}
