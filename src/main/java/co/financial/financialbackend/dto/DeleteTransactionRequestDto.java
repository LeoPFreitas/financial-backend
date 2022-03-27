package co.financial.financialbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeleteTransactionRequestDto {
    @NotNull(message = "AccountId cannot be null or empty")
    private Long accountId;

    @NotNull(message = "TransactionId cannot be null or empty")
    private Long transactionId;
}
