package com.viaje.viaje.repository;

import com.viaje.viaje.model.Tags;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagsRepository extends JpaRepository<Tags, Long> {
    Tags findByTagName(String tagName);
}
