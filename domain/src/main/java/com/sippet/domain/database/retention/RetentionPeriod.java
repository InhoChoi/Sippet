package com.sippet.domain.database.retention;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Entity
@Builder
@Table(name = "retention_period",
        uniqueConstraints = {@UniqueConstraint(name = "retention_period_idx01", columnNames = {"trackingId", "eventDate"})})
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
public class RetentionPeriod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String trackingId;

    private Long retentionPeriod;

    private LocalDate eventDate;

    @CreatedDate
    private LocalDateTime createAt;
}
