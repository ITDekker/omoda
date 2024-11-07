package nl.omoda.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import nl.omoda.model.ChangeRequest;
import nl.omoda.model.ChangeResponse;
import nl.omoda.service.ChangeCalculatorService;

@CrossOrigin(origins = "*")
@RestController
public class ChangeCalculatorController {

	@Autowired
	private ChangeCalculatorService changeCalculatorService;

	@CrossOrigin(origins = "*")
	@PostMapping("api/calculate-change")
	public ChangeResponse calculateChange(@RequestBody ChangeRequest request) {
		return changeCalculatorService.calculateChange(request);
	}
}