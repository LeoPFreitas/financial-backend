package co.financial.financialbackend.mapper;

import co.financial.financialbackend.dto.RegisterAccountRequestDto;
import co.financial.financialbackend.entity.AccountEntity;
import co.financial.financialbackend.entity.TransactionEntity;
import co.financial.financialbackend.model.Account;
import co.financial.financialbackend.model.Transaction;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class AccountMapper {
    private final ModelMapper mapper;

    public AccountEntity toEntity(Account account) {
        mapper.typeMap(Account.class, AccountEntity.class)
              .addMapping(Account::getBalance, AccountEntity::setBalance)
              .addMapping(Account::getDescription, AccountEntity::setDescription)
              .addMapping(Account::getName, AccountEntity::setName);

        return mapper.map(account, AccountEntity.class);
    }

    public Account toAccount(@NotNull Account account, RegisterAccountRequestDto registerAccountRequestDto) {
        mapper.typeMap(Account.class, AccountEntity.class)
              .addMapping(Account::getBalance, AccountEntity::setBalance)
              .addMapping(Account::getDescription, AccountEntity::setDescription);

        mapper.map(registerAccountRequestDto, account);

        return account;
    }

    public Account toAccount(AccountEntity accountEntity) {
        mapper.typeMap(AccountEntity.class, Account.class)
              .addMapping(AccountEntity::getBalance, Account::setBalance)
              .addMapping(AccountEntity::getDescription, Account::setDescription);

        var account = Account.ofId(accountEntity.getId(), accountEntity.getName());
        mapper.map(accountEntity, account);

        var transactions = accountEntity.getTransactionEntities()
                                        .stream()
                                        .map(toTransaction)
                                        .toList();

        account.getTransactions().addAll(transactions);

        return account;
    }

    private final Function<TransactionEntity, Transaction> toTransaction = entity -> {
        var mapper = new ModelMapper();
        mapper.typeMap(TransactionEntity.class, Transaction.class)
              .addMapping(TransactionEntity::getId, Transaction::setId);

        return mapper.map(entity, Transaction.class);
    };
}
