package co.financial.financialbackend.mapper;

import co.financial.financialbackend.entity.AccountEntity;
import co.financial.financialbackend.model.Account;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccountMapper {
    private final ModelMapper mapper;

    public AccountEntity toEntity(Account account) {
        return mapper.map(account, AccountEntity.class);
    }

    public Account toAccount(AccountEntity accountEntity) {
        var account = new Account(accountEntity.getId());
        return mapper.map(account, Account.class);
    }
}
