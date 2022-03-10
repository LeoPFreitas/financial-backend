package co.financial.financialbackend.model;

import lombok.Data;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Currency;
import java.util.Objects;
import java.util.UUID;

@Data
public class Transaction {
    private final UUID id;
    private BigInteger value;
    private LocalDateTime date;
    private Currency currency;
    private Boolean received = false;
    private String description;
    private Category category;

    @Contract("_ -> new")
    public static @NotNull Transaction ofValue(BigInteger value) {
        if (Objects.isNull(value)) {
            throw new IllegalArgumentException("Transaction value cannot be null or empty");
        }
        return new Transaction(value);
    }

    private Transaction(BigInteger value) {
        this.id = UUID.randomUUID();
        this.value = value;
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
