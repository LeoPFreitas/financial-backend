package co.financial.financialbackend.model;

import lombok.Data;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.function.Predicate;

@Data
public class Account {
    private final UUID id;
    private BigInteger balance;
    private BigInteger projectedBalance;
    private String description;

    private Map<UUID, Transaction> transactions;

    public Account() {
        this.id = UUID.randomUUID();
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

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Account account = (Account) o;
        return id.equals(account.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
