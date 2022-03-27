package co.financial.financialbackend.service;


import co.financial.financialbackend.dto.DeleteTransactionRequestDto;
import co.financial.financialbackend.dto.RegisterTransactionRequestDto;
import co.financial.financialbackend.dto.UpdateTransactionRequestDto;
import co.financial.financialbackend.mapper.AccountMapper;
import co.financial.financialbackend.mapper.TransactionMapper;
import co.financial.financialbackend.repository.AccountRepository;
import co.financial.financialbackend.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
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

    public Long registerTransaction(@NotNull RegisterTransactionRequestDto registerTransactionRequestDto) {
        if (Objects.isNull(registerTransactionRequestDto.getAccountId())) {
            throw new IllegalArgumentException("AccountId cannot be null or empty.");
        }
        var accountEntity = accountRepository.findById(registerTransactionRequestDto.getAccountId())
                                             .orElseThrow(() -> new IllegalArgumentException("Account not found."));

        var account = accountMapper.toAccount(accountEntity);

        var amount = registerTransactionRequestDto.getAmount();
        if (Objects.isNull(amount))
            throw new IllegalArgumentException("Transaction amount cannot be null or empty");

        var transaction = transactionMapper.toTransaction(registerTransactionRequestDto);
        log.info("Successfully build Transaction [%s]".formatted(transaction.toString()));

        var transactionEntity = transactionMapper.toEntity(transaction);
        transactionEntity.setAccountEntity(accountEntity);
        var savedTransactionEntity = transactionRepository.save(transactionEntity);

        var transactionId = savedTransactionEntity.getId();
        log.info("Transaction [%s] successfully created.".formatted(transactionId));
        return transactionId;
    }

    public Long updateTransaction(@NotNull UpdateTransactionRequestDto requestDto) {
        var transactionId = requestDto.getTransactionId();
        var accountId = requestDto.getAccountId();

        if (Objects.isNull(transactionId) || Objects.isNull(accountId))
            throw new IllegalArgumentException("AccountId and TransactionId cannot be null or empty");


        var accountEntity = accountRepository.findById(accountId)
                                             .orElseThrow(() -> new IllegalArgumentException("Account not found."))
                                             .getTransactionEntities()
                                             .stream()
                                             .filter(t -> t.getId().equals(transactionId))
                                             .findAny()
                                             .orElseThrow(() -> new IllegalArgumentException("Transaction not founded!"))
                                             .getAccountEntity();


        var transactionEntity = transactionMapper.toEntity(requestDto);
        transactionEntity.setAccountEntity(accountEntity);
        transactionRepository.save(transactionEntity);

        log.info("Successfully updated Transaction ID [%s]".formatted(transactionId));
        return transactionId;
    }

    public Long deleteTransaction(@NotNull DeleteTransactionRequestDto requestDto) {
        var transactionId = requestDto.getTransactionId();
        var accountId = requestDto.getAccountId();

        if (Objects.isNull(transactionId) || Objects.isNull(accountId))
            throw new IllegalArgumentException("AccountId and TransactionId cannot be null or empty");


        var transactionEntity = accountRepository.findById(accountId)
                                                 .orElseThrow(() -> new IllegalArgumentException("Account not found."))
                                                 .getTransactionEntities()
                                                 .stream()
                                                 .filter(t -> t.getId().equals(transactionId))
                                                 .findAny()
                                                 .orElseThrow(() -> new IllegalArgumentException("Transaction not founded!"));


        transactionRepository.delete(transactionEntity);

        log.info("Successfully deleted Transaction ID [%s]".formatted(transactionId));
        return transactionId;
    }
}
