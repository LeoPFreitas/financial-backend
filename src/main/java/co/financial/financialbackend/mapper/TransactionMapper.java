package co.financial.financialbackend.mapper;

import co.financial.financialbackend.dto.RegisterTransactionRequestDto;
import co.financial.financialbackend.entity.TransactionEntity;
import co.financial.financialbackend.model.Transaction;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TransactionMapper {
    private final ModelMapper mapper;

    public Transaction toTransaction(RegisterTransactionRequestDto dto, Transaction transaction) {
        mapper.map(dto, transaction);
        return transaction;
    }

    public TransactionEntity toEntity(Transaction transaction) {
        TypeMap<Transaction, TransactionEntity> typeMap = this.mapper.createTypeMap(transaction, TransactionEntity.class);
        typeMap.addMappings(mapping -> mapping.skip(TransactionEntity::setId))
               .addMapping(Transaction::getId, TransactionEntity::setTransactionId);

        return mapper.map(transaction, TransactionEntity.class);
    }
}
