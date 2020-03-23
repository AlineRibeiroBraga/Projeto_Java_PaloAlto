package br.com.invillia.projetoPaloAlto.anotation;

import br.com.invillia.projetoPaloAlto.CNPJValidator;
import br.com.invillia.projetoPaloAlto.utils.Messages;
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = CNPJValidator.class)
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
public @interface IsCNPJ {

    String message() default Messages.NOT_DOCUMENT;
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}