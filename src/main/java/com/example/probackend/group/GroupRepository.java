package com.example.probackend.group;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GroupRepository
        extends JpaRepository<GroupEntity,Long> {

    Optional<GroupEntity> findByName(String name);
}
