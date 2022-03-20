package co.financial.financialbackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    private Long id;
    private BigDecimal amount;
    private LocalDateTime date;
    private Currency currency;
    private Boolean received = false;
    private String description;
    private Category category;

    @Contract("_ -> new")
    public static @NotNull Transaction ofValue(BigDecimal amount) {
        if (Objects.isNull(amount)) {
            throw new IllegalArgumentException("Transaction value cannot be null or empty");
        }
        return new Transaction(amount);
    }

    public static @NotNull Transaction ofValue(Long id, BigDecimal amount) {
        if (Objects.isNull(amount) || Objects.isNull(id)) {
            throw new IllegalArgumentException("Transaction amount or ID cannot be null or empty");
        }
        return new Transaction(id, amount);
    }

    private Transaction(Long id, BigDecimal amount) {
        this.id = id;
        this.amount = amount;
    }

    private Transaction(BigDecimal amount) {
        this.amount = amount;
    }
}
