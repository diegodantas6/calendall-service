package br.com.calendall.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import br.com.calendall.dto.ret.ErroRET;

@Stateless
public class BeanValidator {

	public List<ErroRET> validar(Object object) {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();

		Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object);

		List<ErroRET> erros = new ArrayList<>();
		
		if (constraintViolations.size() > 0 ) {

			for (ConstraintViolation<Object> contraints : constraintViolations) {
				ErroRET erro = new ErroRET(contraints.getPropertyPath().toString(), contraints.getMessage());
				erros.add(erro);
			}
		}
		
		return erros;
	}
}
