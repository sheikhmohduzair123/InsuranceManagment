package com.Insurance.Management.Platform.repo;

import com.Insurance.Management.Platform.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {
      Optional<Client> findByName(String username);
}
