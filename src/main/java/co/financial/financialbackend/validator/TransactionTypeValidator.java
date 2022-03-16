//package co.financial.financialbackend.validator;
//
//import javax.validation.ConstraintValidator;
//import javax.validation.ConstraintValidatorContext;
//import java.util.List;
//import java.util.stream.Stream;
//
//public class TransactionTypeValidator implements ConstraintValidator<ValidTransactionType, Integer> {
//    private List<Integer> acceptedCodes;
//
//    @Override
//    public void initialize(ValidTransactionType constraintAnnotation) {
//
//        Enum<?>[] enumConstants = constraintAnnotation.enumClass().getEnumConstants();
//
//        Stream.of(constraintAnnotation.enumClass().getEnumConstants())
//
//    }
//
//    @Override
//    public boolean isValid(Integer value, ConstraintValidatorContext context) {
//        return false;
//    }
//}
