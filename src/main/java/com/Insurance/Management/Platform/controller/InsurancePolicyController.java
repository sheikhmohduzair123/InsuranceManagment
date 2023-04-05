package com.Insurance.Management.Platform.controller;


import com.Insurance.Management.Platform.exception.ResourceNotFoundException;
import com.Insurance.Management.Platform.model.InsurancePolicy;
import com.Insurance.Management.Platform.repo.InsurancePolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/policies")
public class InsurancePolicyController {

    @Autowired
    private InsurancePolicyRepository insurancePolicyRepository;

    // Fetch all insurance policies
    @GetMapping
    public List<InsurancePolicy> getAllPolicies() {
        return insurancePolicyRepository.findAll();
    }

    // Fetch a specific insurance policy by ID
    @GetMapping("/{id}")
    public ResponseEntity<InsurancePolicy> getPolicyById(@PathVariable(value = "id") Long policyId)
            throws ResourceNotFoundException {
        InsurancePolicy policy = insurancePolicyRepository.findById(policyId)
                .orElseThrow(() -> new ResourceNotFoundException("Policy not found for this id :: " + policyId));
        return ResponseEntity.ok().body(policy);
    }

    // Create a new insurance policy
    @PostMapping
    public InsurancePolicy createPolicy( @RequestBody InsurancePolicy policy) {
        return insurancePolicyRepository.save(policy);
    }

    // Update an insurance policy
    @PutMapping("/{id}")
    public ResponseEntity<InsurancePolicy> updatePolicy(@PathVariable(value = "id") Long policyId,
                                                         @RequestBody InsurancePolicy policyDetails)
            throws ResourceNotFoundException {
        InsurancePolicy policy = insurancePolicyRepository.findById(policyId)
                .orElseThrow(() -> new ResourceNotFoundException("Policy not found for this id :: " + policyId));

        policy.setPolicyNumber(policyDetails.getPolicyNumber());
        policy.setType(policyDetails.getType());
        policy.setCoverageAmount(policyDetails.getCoverageAmount());
        policy.setPremium(policyDetails.getPremium());
        policy.setStartDate(policyDetails.getStartDate());
        policy.setEndDate(policyDetails.getEndDate());
        final InsurancePolicy updatedPolicy = insurancePolicyRepository.save(policy);
        return ResponseEntity.ok(updatedPolicy);
    }

    // Delete an insurance policy
    @DeleteMapping("/{id}")
    public Map<String, Boolean> deletePolicy(@PathVariable(value = "id") Long policyId)
            throws ResourceNotFoundException {
        InsurancePolicy policy = insurancePolicyRepository.findById(policyId)
                .orElseThrow(() -> new ResourceNotFoundException("Policy not found for this id :: " + policyId));

        insurancePolicyRepository.delete(policy);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}


