package com.sippet.domain.domain.retention;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Getter
@Entity
@Builder
@EntityListeners(AuditingEntityListener.class)
public class RetentionPeriod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tracking_id;

    private Long retention_period;
}
