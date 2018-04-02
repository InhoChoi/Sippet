package com.sippet.domain.domain.retention;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Getter
@Entity
@Builder
@Table(name = "retention_period")
@EntityListeners(AuditingEntityListener.class)
public class RetentionPeriod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String trackingId;

    private Long retentionPeriod;
}
