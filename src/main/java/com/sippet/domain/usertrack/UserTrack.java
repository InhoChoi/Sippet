package com.sippet.domain.usertrack;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class UserTrack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String host;

    private String href;

    private String pathName;

    private String referrer;

    @CreatedDate
    private LocalDateTime createdAt;

    private String trackingId;

    private Boolean newVisitor;
}
