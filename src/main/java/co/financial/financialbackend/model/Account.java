package co.financial.financialbackend.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.function.Predicate;

@Data
@EqualsAndHashCode
public class Account {
    private Long id;
    private BigDecimal balance;
    private BigDecimal projectedBalance;
    private String name;
    private String description;

    private Map<UUID, Transaction> transactions;

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
        transactions = new HashMap<>();
    }

    private Account(String name) {
        this.name = name;
        this.balance = BigDecimal.ZERO;
        this.projectedBalance = BigDecimal.ZERO;
        transactions = new HashMap<>();
    }

    public UUID registerTransaction(Transaction transaction) {
        if (isNull.test(transaction))
            throw new IllegalArgumentException("Transaction cannot be null  or empty");

        this.transactions.put(transaction.getId(), transaction);

        return transaction.getId();
    }

    private Predicate<Transaction> isNull = transaction -> Objects.isNull(transaction) || Objects.isNull(transaction.getId());

    private Predicate<Transaction> transactionExists = t -> this.transactions.containsKey(t.getId());
}
