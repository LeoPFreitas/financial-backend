//package co.financial.financialbackend.validator;
//
//import javax.validation.Constraint;
//import javax.validation.Payload;
//import java.lang.annotation.Documented;
//import java.lang.annotation.Retention;
//import java.lang.annotation.Target;
//
//import static java.lang.annotation.ElementType.*;
//import static java.lang.annotation.RetentionPolicy.RUNTIME;
//
//@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
//@Retention(RUNTIME)
//@Documented
//@Constraint(validatedBy = TransactionTypeValidator.class)
//public @interface ValidTransactionType {
//    String message() default "Must be any of enum {enumClass}";
//
//    Class<?>[] groups() default {};
//
//    Class<? extends Payload>[] payload() default {};
//
//    Class<? extends Enum<?>> enumClass();
//}
