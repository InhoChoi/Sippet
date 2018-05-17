package com.sippet.domain.domain.userstatistics;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Getter
@Entity
@ToString
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
public class UserStatistics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String host;

    private String href;

    private String pathName;

    private String referrerHost;

    private String referrerPath;

    private Long visitCount;

    @CreatedDate
    private LocalDate date;
}
