package org.jog.springsecuritygit.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@RequiredArgsConstructor
public class ProductDTO implements Serializable {


    @Serial
    private static final long serialVersionUID = -878799638216166110L;

    @NotBlank
    private String name;

    @DecimalMin(value = "0.01")
    private BigDecimal price;

    @Min(value = 1)
    private Long categoryId;
}
