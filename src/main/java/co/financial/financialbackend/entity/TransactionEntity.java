package co.financial.financialbackend.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@Table(name = "transactions")
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "value", nullable = false)
    private BigInteger value;

    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    @Column(name = "type", nullable = false)
    private TransactionTypeEntity type;

    @Column(name = "received", nullable = false)
    private Boolean received = false;

    @OneToOne(optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    private TransactionCategoryEntity category;

    @Column(name = "description")
    private String description;
}