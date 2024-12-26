package com.feirinha.api.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class ItemsDTO {

    @NotBlank(message = "Field name cannot be null")
    @Size(min = 3, max = 150, message = "Length for name is 3 to 150 characters!")
    private String name;

    @Positive(message = "quantity should be greater than zero")
    @NotNull(message = "Field quantity cannot be null")
    private Integer quantity;
}
