package com.ilkiz.rabbitmq.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ActivatedRequestDto implements Serializable {

    Long id;
    String activatedCode;
    String email;
}
