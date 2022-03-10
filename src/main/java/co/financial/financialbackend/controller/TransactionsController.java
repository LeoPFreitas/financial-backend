package co.financial.financialbackend.controller;

import co.financial.financialbackend.dto.RegisterTransactionRequestDto;
import co.financial.financialbackend.dto.RegisterTransactionResponseDto;
import co.financial.financialbackend.service.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@RequestMapping("/api/v1/transactions/")
@RequiredArgsConstructor
public class TransactionsController {
    private final TransactionService transactionService;

    @PostMapping(value = "/transaction", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<RegisterTransactionResponseDto> register(@Valid @RequestBody RegisterTransactionRequestDto requestDto) {
        log.debug("Create Transaction Operation -- Payload [%s]".formatted(requestDto.toString()));
        try {
            transactionService.registerTransaction(requestDto);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return ResponseEntity.ok().build();
    }
}
