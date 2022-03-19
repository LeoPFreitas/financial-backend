package co.financial.financialbackend.controller;

import co.financial.financialbackend.dto.AccountId;
import co.financial.financialbackend.dto.RegisterAccountRequestDto;
import co.financial.financialbackend.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;

import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@RequestMapping("/api/v1/accounts/")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping(value = "/account", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> register(@Valid @RequestBody RegisterAccountRequestDto requestDto) {
        log.debug("Create Account Operation -- Payload [%s]".formatted(requestDto.toString()));

        var accountId = accountService.registerAccount(requestDto);


        return ResponseEntity.status(CREATED).body(accountId);
    }

    @GetMapping(value = "/account", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> register(@RequestBody AccountId accountId) {
        log.debug("Retrieve Account Operation -- ID [%s]".formatted(accountId));

        if (Objects.isNull(accountId))
            return ResponseEntity.badRequest().body("Account ID cannot be null or empty");

        var accountEntity = accountService.retrieveAccount(accountId.accountId());

        if (Objects.isNull(accountEntity)) {
            return ResponseEntity.status(ACCEPTED).body("Account not founded!");
        }

        return ResponseEntity.status(CREATED).body(accountEntity);
    }
}
