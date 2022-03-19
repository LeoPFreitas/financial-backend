package co.financial.financialbackend.service;

import co.financial.financialbackend.dto.RegisterAccountRequestDto;
import co.financial.financialbackend.mapper.AccountMapper;
import co.financial.financialbackend.model.Account;
import co.financial.financialbackend.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final AccountMapper mapper;

    public Long registerAccount(RegisterAccountRequestDto requestDto) {
        var account = Account.newAccount(requestDto.getName());
        account = mapper.toAccount(account, requestDto);

        var accountEntity = mapper.toEntity(account);
        accountEntity = accountRepository.save(accountEntity);

        return accountEntity.getId();
    }

    public Account retrieveAccount(Long accountId) {
        var byId = accountRepository.findById(accountId);

        if (byId.isPresent()) {
            var accountEntity = byId.get();
            return mapper.toAccount(accountEntity);
        }

        return null;
    }
}
