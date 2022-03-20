package co.financial.financialbackend.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;

@Data
@EqualsAndHashCode
public class Account {
    private Long id;
    private BigDecimal balance;
    private BigDecimal projectedBalance;
    private String name;
    private String description;

    private Set<Transaction> transactions;

    public static @NotNull Account ofId(Long id, String name) {
        if (Objects.isNull(id)) {
            throw new IllegalArgumentException("Account ID cannot be null or empty");
        }
        return new Account(id, name);
    }

    public static @NotNull Account newAccount(String name) {
        return new Account(name);
    }

    private Account(Long id, String name) {
        this.name = name;
        this.id = id;
        this.balance = BigDecimal.ZERO;
        this.projectedBalance = BigDecimal.ZERO;
        transactions = new HashSet<>();
    }

    private Account(String name) {
        this.name = name;
        this.balance = BigDecimal.ZERO;
        this.projectedBalance = BigDecimal.ZERO;
        transactions = new HashSet<>();
    }

    public Long registerTransaction(Transaction transaction) {
        if (Objects.isNull(transaction))
            throw new IllegalArgumentException("Transaction cannot be null  or empty");

        this.transactions.add(transaction);

        return transaction.getId();
    }

    private Predicate<Transaction> transactionExists = t -> this.transactions.contains(t);
}
