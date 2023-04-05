package com.Insurance.Management.Platform.repo;

import com.Insurance.Management.Platform.model.Claim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClaimRepository  extends JpaRepository<Claim,Long> {
}
