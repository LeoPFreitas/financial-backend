package co.financial.financialbackend.mapper;

import co.financial.financialbackend.dto.RegisterTransactionRequestDto;
import co.financial.financialbackend.entity.TransactionEntity;
import co.financial.financialbackend.model.Transaction;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Currency;

@Component
@RequiredArgsConstructor
public class TransactionMapper {
    private final ModelMapper mapper;

    public Transaction toTransaction(RegisterTransactionRequestDto dto) {
        mapper.typeMap(RegisterTransactionRequestDto.class, Transaction.class)
              .addMappings(mp -> mp.skip(Transaction::setId));

        var transaction = Transaction.ofValue(dto.getAmount());

        var currency = Currency.getInstance(dto.getCurrencyCode());
        transaction.setCurrency(currency);

        mapper.map(dto, transaction);
        return transaction;
    }

    public Transaction toTransaction(TransactionEntity entity) {
        return mapper.map(entity, Transaction.class);
    }

    public TransactionEntity toEntity(Transaction transaction) {
        return mapper.map(transaction, TransactionEntity.class);
    }
}
