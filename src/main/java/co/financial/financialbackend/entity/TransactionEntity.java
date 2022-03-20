package co.financial.financialbackend.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "transactions")
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "account_entity_id")
    private AccountEntity accountEntity;

    @Override
    public String toString() {
        return "TransactionEntity{" +
                "id=" + id +
                ", amount=" + amount +
                ", date=" + date +
                '}';
    }
}