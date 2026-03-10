package com.devsuperior.dsmeta.controllers;

import com.devsuperior.dsmeta.dto.SummaryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.services.SaleService;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {

	@Autowired
	private SaleService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<SaleMinDTO> findById(@PathVariable Long id) {
		SaleMinDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}

	@GetMapping(value = "/report")
	public ResponseEntity<?> getReport() {
		// TODO
		return null;
	}

	@GetMapping(value = "/summary")
	public ResponseEntity<List<SummaryDTO>> getSummary(
			@RequestParam(name = "minDate", defaultValue = "") String minDate,
			@RequestParam(name = "maxDate", defaultValue = "")  String maxDate) {

		LocalDate max;
		LocalDate min;

		if (maxDate.isBlank()) {
			max = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		} else {
			max = LocalDate.parse(maxDate);
		}

		if (minDate.isBlank()) {
			min = max.minusYears(1L);
		} else {
			min = LocalDate.parse(minDate);
		}

		return ResponseEntity.ok(service.searchSellerNameAndTotal(min, max));
	}
}
