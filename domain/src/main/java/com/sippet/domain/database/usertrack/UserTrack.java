package com.sippet.domain.database.usertrack;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@Getter
@Entity
@ToString
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
public class UserTrack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String host;

    private String href;

    private String pathName;

    private String referrerHost;

    private String referrerPath;

    @CreatedDate
    private LocalDateTime createdAt;

    private String trackingId;

    private Boolean newVisitor;
}
