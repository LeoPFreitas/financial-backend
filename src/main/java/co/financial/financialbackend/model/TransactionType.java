package co.financial.financialbackend.model;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

public enum TransactionType {
    EXPENSE(1),
    INCOME(2),
    CREDIT_CARD(3),
    INVESTMENT(4),

    UNSET(-1);

    private final Integer code;

    TransactionType(int code) {
        this.code = code;
    }

    public static Optional<TransactionType> fromTypeCode(Integer code) {
        return Arrays.stream(values())
                     .filter(type -> Objects.equals(type.code, code))
                     .findFirst();
    }

    public static boolean isValidEnum(Integer typeCode) {
        return Arrays.stream(values())
                     .anyMatch(type -> Objects.equals(type.code, typeCode));
    }

    @Override
    public String toString() {
        return this.code.toString();
    }

    public Integer getCode() {
        return code;
    }
}
