package co.financial.financialbackend.model;

import lombok.Data;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;
import java.util.Objects;
import java.util.UUID;

@Data
public class Transaction {
    private final UUID id;
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

    private Transaction(BigDecimal amount) {
        this.id = UUID.randomUUID();
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Transaction that = (Transaction) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
