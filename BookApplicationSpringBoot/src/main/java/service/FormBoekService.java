package service;

import formObjecten.FormBoek;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

public interface FormBoekService {

	
	@Transactional
	void updateOrAddFormBoek(@Valid FormBoek formboek);
}
