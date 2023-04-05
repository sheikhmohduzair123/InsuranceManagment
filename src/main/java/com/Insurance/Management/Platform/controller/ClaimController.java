package com.Insurance.Management.Platform.controller;


import com.Insurance.Management.Platform.exception.ResourceNotFoundException;
import com.Insurance.Management.Platform.model.Claim;
import com.Insurance.Management.Platform.repo.ClaimRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/claims")
public class ClaimController {

    @Autowired
    private ClaimRepository claimRepository;

    // Fetch all claims
    @GetMapping
    public List<Claim> getAllClaims() {
        return claimRepository.findAll();
    }

    // Fetch a specific claim by ID
    @GetMapping("/{id}")
    public ResponseEntity<Claim> getClaimById(@PathVariable(value = "id") Long claimId)
            throws ResourceNotFoundException {
        Claim claim = claimRepository.findById(claimId)
                .orElseThrow(() -> new ResourceNotFoundException("Claim not found for this id :: " + claimId));
        return ResponseEntity.ok().body(claim);
    }

    // Create a new claim
    @PostMapping
    public Claim createClaim(@RequestBody Claim claim) {
        return claimRepository.save(claim);
    }

    // Update a claim's information
    @PutMapping("/{id}")
    public ResponseEntity<Claim> updateClaim(@PathVariable(value = "id") Long claimId,
                                             @Validated @RequestBody Claim claimDetails) throws ResourceNotFoundException {
        Claim claim = claimRepository.findById(claimId)
                .orElseThrow(() -> new ResourceNotFoundException("Claim not found for this id :: " + claimId));

        claim.setClaimNumber(claimDetails.getClaimNumber());
        claim.setDescription(claimDetails.getDescription());
        claim.setClaimDate(claimDetails.getClaimDate());
        claim.setClaimStatus(claimDetails.getClaimStatus());
        final Claim updatedClaim = claimRepository.save(claim);
        return ResponseEntity.ok(updatedClaim);
    }

    // Delete a claim
    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteClaim(@PathVariable(value = "id") Long claimId)
            throws ResourceNotFoundException {
        Claim claim = claimRepository.findById(claimId)
                .orElseThrow(() -> new ResourceNotFoundException("Claim not found for this id :: " + claimId));

        claimRepository.delete(claim);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}


