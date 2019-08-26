package br.com.gestaoescala.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.gestaoescala.domain.Servidor;
import br.com.gestaoescala.dto.ServidorDTO;
import br.com.gestaoescala.repositories.ServidorRepository;
import br.com.gestaoescala.resources.exception.FieldMessage;

public class ServidorUpdateValidator implements ConstraintValidator<ServidorUpdate, ServidorDTO>{
	
	@Autowired
	private ServidorRepository repo;
	
	@Override
	public void initialize(ServidorUpdate ann) {
	}

	@Override
	public boolean isValid(ServidorDTO objDto, ConstraintValidatorContext context) {
		
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
