package br.com.calendall.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import br.com.calendall.dto.out.ErroOUT;

@Stateless
public class BeanValidator {

	public List<ErroOUT> validar(Object object) {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();

		Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object);

		List<ErroOUT> erros = new ArrayList<>();
		
		if (constraintViolations.size() > 0 ) {

			for (ConstraintViolation<Object> contraints : constraintViolations) {
				ErroOUT erro = new ErroOUT(contraints.getPropertyPath().toString(), contraints.getMessage());
				erros.add(erro);
			}
		}
		
		return erros;
	}
	
	@SuppressWarnings("rawtypes")
	public List<ErroOUT> validarList(List objects) {
		List<ErroOUT> erros = new ArrayList<>();
		
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();

		for (Object object : objects) {
			Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object);
			
			if (constraintViolations.size() > 0 ) {

				for (ConstraintViolation<Object> contraints : constraintViolations) {
					ErroOUT erro = new ErroOUT(contraints.getPropertyPath().toString(), contraints.getMessage());
					erros.add(erro);
				}
			}
		}
		
		return erros;
	}
}
