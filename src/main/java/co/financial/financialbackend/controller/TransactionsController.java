package co.financial.financialbackend.controller;

import co.financial.financialbackend.dto.DeleteTransactionRequestDto;
import co.financial.financialbackend.dto.RegisterTransactionRequestDto;
import co.financial.financialbackend.dto.UpdateTransactionRequestDto;
import co.financial.financialbackend.service.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@RequestMapping("/api/v1/transactions/")
@RequiredArgsConstructor
public class TransactionsController {
    private final TransactionService transactionService;

    @PostMapping(value = "/transaction", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> register(@Valid @RequestBody RegisterTransactionRequestDto requestDto) {
        log.debug("Create Transaction Operation -- Payload [%s]".formatted(requestDto.toString()));
        try {
            var transaction = transactionService.registerTransaction(requestDto);
            return ResponseEntity.ok().body(transaction);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping(value = "/transaction", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> update(@Valid @RequestBody UpdateTransactionRequestDto requestDto) {
        log.debug("Update Transaction Operation -- Payload [%s]".formatted(requestDto.toString()));
        try {
            var transaction = transactionService.updateTransaction(requestDto);
            return ResponseEntity.ok().body(transaction);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping(value = "/transaction", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> update(@Valid @RequestBody DeleteTransactionRequestDto requestDto) {
        log.debug("Delete Transaction Operation -- Payload [%s]".formatted(requestDto.toString()));
        try {
            var transaction = transactionService.deleteTransaction(requestDto);
            return ResponseEntity.ok().body(transaction);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return ResponseEntity.badRequest().build();
    }
}
