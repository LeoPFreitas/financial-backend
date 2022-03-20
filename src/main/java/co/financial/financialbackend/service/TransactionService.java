package co.financial.financialbackend.service;


import co.financial.financialbackend.dto.RegisterTransactionRequestDto;
import co.financial.financialbackend.mapper.AccountMapper;
import co.financial.financialbackend.mapper.TransactionMapper;
import co.financial.financialbackend.repository.AccountRepository;
import co.financial.financialbackend.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionMapper transactionMapper;
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    public Long registerTransaction(RegisterTransactionRequestDto registerTransactionRequestDto) {
        if (Objects.isNull(registerTransactionRequestDto.getAccountId())) {
            throw new IllegalArgumentException("AccountId cannot be null or empty.");
        }
        var accountEntity = accountRepository.findById(registerTransactionRequestDto.getAccountId())
                                             .orElseThrow(() -> new IllegalArgumentException("Account not found."));

        var account = accountMapper.toAccount(accountEntity);

        // create transaction domain
        var amount = registerTransactionRequestDto.getAmount();
        if (Objects.isNull(amount))
            throw new IllegalArgumentException("Transaction amount cannot be null or empty");

//        var transaction = Transaction.ofValue(amount);
        var transaction = transactionMapper.toTransaction(registerTransactionRequestDto);
        log.info("Successfully build Transaction [%s]".formatted(transaction.toString()));

        // persist
        var transactionEntity = transactionMapper.toEntity(transaction);
        transactionEntity.setAccountEntity(accountEntity);
        var savedTransactionEntity = transactionRepository.save(transactionEntity);

        var transactionId = savedTransactionEntity.getId();
        log.info("Transaction [%s] successfully created.".formatted(transactionId));
        return transactionId;
    }
}
