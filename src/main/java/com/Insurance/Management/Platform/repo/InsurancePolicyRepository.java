package com.Insurance.Management.Platform.repo;

import com.Insurance.Management.Platform.model.InsurancePolicy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InsurancePolicyRepository  extends JpaRepository<InsurancePolicy,Long> {
}
