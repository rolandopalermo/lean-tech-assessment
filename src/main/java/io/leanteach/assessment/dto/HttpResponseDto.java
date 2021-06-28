package io.leanteach.assessment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HttpResponseDto<T> implements Serializable {

    private boolean success;
    private T result;
}
