package com.sippet.domain.domain.retention;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class RetentionPeriodDto {
    private String tracking_id;

    private Long retention_period;
}
