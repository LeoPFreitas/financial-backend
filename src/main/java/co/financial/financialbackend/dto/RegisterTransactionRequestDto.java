package co.financial.financialbackend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterTransactionRequestDto {
    @NotNull(message = "AccountId cannot be null or empty")
    private Long accountId;

    @NotNull(message = "Value cannot be null or empty")
    @PositiveOrZero(message = "Value cannot be a negative number")
    private BigDecimal amount;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss[.SSS][.SS][.S]")
    private LocalDateTime date;

    @Pattern(regexp = "\\b[A-Z]{3}\\b", message = "A valid currency code must have 3 uppercase letters.")
    private String currencyCode;

    private Boolean received;

    private String description;
}
