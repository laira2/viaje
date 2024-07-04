package com.viaje.viaje.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@IdClass(PlanTag.PlanTagId.class)
public class PlanTag {
    @Id
    private Long planId;

    @Id
    private Long tagId;

    @ManyToOne
    @JoinColumn(name = "planId", insertable = false, updatable = false)
    private TravelPlans travelPlans;

    @ManyToOne
    @JoinColumn(name = "tagId", insertable = false, updatable = false)
    private Tags tag;

    // 기타 필요한 메서드들

    @Embeddable
    public static class PlanTagId implements Serializable {
        private Long planId;
        private Long tagId;

        // 생성자, getter, setter, equals, hashCode 메서드
    }
}


