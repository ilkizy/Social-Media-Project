package com.ilkiz.rabbitmq.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ActivatedRequestDto implements Serializable {

    Long id;
    String email;
    String activatedCode;
}
