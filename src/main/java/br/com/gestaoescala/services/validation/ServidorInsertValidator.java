package br.com.gestaoescala.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.gestaoescala.controllers.excpetions.FieldMessage;
import br.com.gestaoescala.domain.Servidor;
import br.com.gestaoescala.dto.ServidorNewDTO;
import br.com.gestaoescala.repositories.ServidorRepository;

public class ServidorInsertValidator implements ConstraintValidator<ServidorInsert, ServidorNewDTO>{
	
	@Autowired
	private ServidorRepository repo;
	
	@Override
	public void initialize(ServidorInsert ann) {
	}

	@Override
	public boolean isValid(ServidorNewDTO objDto, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();
		
		Servidor aux = repo.findByEmail(objDto.getEmail());
		if (aux != null) {
			list.add(new FieldMessage("email", "E-mail "+ objDto.getEmail()+", já existente"));
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
