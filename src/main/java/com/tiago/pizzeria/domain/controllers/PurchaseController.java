package com.tiago.pizzeria.domain.controllers;

import com.tiago.pizzeria.domain.models.Pizza;
import com.tiago.pizzeria.domain.models.Purchase;
import com.tiago.pizzeria.domain.services.PurchaseService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {

	@Autowired
	private PurchaseService purchaseService;

	public PurchaseController() {

	}

	@PostMapping("pizza")
	@Operation(summary = "My endpoint", security = @SecurityRequirement(name = "bearerAuth"))
	public ResponseEntity<Purchase> addToBasket(@RequestBody Pizza pizza) {
		Purchase purchase = purchaseService.addPizzaToPurchase(pizza);
		return new ResponseEntity<>(purchase, HttpStatus.OK);
	}

	@PostMapping("order")
	public ResponseEntity<Void> submitPurchase() {
		purchaseService.confirmPurchase();
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@GetMapping("pick")
	public ResponseEntity<Purchase> pickPurchase() {
		Purchase purchase = purchaseService.pickPurchase();
		return new ResponseEntity<>(purchase, HttpStatus.OK);
	}

	@PutMapping("complete/{id}")
	public ResponseEntity<Void> completePurchase(@PathVariable("id") long id) {
		purchaseService.completePurchase(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("current")
	public ResponseEntity<Purchase> getCurrentPurchase() {
		Purchase purchase = purchaseService.getCurrentPurchase();
		if (purchase != null) {
			return new ResponseEntity<>(purchase, HttpStatus.FOUND);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}
}
