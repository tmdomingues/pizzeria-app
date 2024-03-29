package com.tiago.pizzeria.domain.controllers;

import com.tiago.pizzeria.domain.services.ManagementService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ManagementController {

    @Autowired
    private ManagementService managementService;

    public ManagementController() {
    }

    @GetMapping("/manage/statistic/purchases")
    public long getPurchasesCount() {
        return managementService.getPurchasesCount();
    }
}
