package co.financial.financialbackend.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.function.Predicate;

@Data
@EqualsAndHashCode
public class Account {
    private final UUID id;
    private BigInteger balance;
    private BigInteger projectedBalance;
    private String name;
    private String description;

    private Map<UUID, Transaction> transactions;

    public Account(UUID id) {
        this.id = id;
        this.name = name;
        this.balance = BigInteger.ZERO;
        this.projectedBalance = BigInteger.ZERO;
        transactions = new HashMap<>();
    }

    public Account(String name) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.balance = BigInteger.ZERO;
        this.projectedBalance = BigInteger.ZERO;
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
