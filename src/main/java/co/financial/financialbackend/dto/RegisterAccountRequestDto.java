package co.financial.financialbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterAccountRequestDto {
    @NotNull(message = "Name cannot be null or empty")
    private String name;

    private String description;
}
