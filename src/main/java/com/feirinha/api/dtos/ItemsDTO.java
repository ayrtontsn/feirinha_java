package com.feirinha.api.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class ItemsDTO {

    @NotBlank
    @Size(min = 3, max = 150, message = "Length for name is 3 to 150 characters!")
    private String name;

    @Positive
    @NotNull
    private Integer quantity;
}
