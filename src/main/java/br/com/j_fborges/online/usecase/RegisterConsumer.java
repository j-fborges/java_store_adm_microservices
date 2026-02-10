package br.com.j_fborges.online.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.j_fborges.online.domain.Consumer;
import br.com.j_fborges.online.repository.IConsumerRepository;
import jakarta.validation.Valid;

@Service
public class RegisterConsumer {
	
private IConsumerRepository consumerRepository;
	
	@Autowired
	public RegisterConsumer(IConsumerRepository consumerRepository) {
		this.consumerRepository = consumerRepository;
	}
	
	public Consumer register(@Valid Consumer consumer) {
		return this.consumerRepository.insert(consumer);
	}

	public Consumer update(@Valid Consumer consumer) {
		return this.consumerRepository.save(consumer);
	}

	public void remove(String id) {
		this.consumerRepository.deleteById(id);
	}
}
