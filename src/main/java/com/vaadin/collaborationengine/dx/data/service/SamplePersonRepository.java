package com.vaadin.collaborationengine.dx.data.service;

import com.vaadin.collaborationengine.dx.data.entity.SamplePerson;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SamplePersonRepository extends JpaRepository<SamplePerson, UUID> {

}