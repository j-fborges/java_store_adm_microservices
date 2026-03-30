package br.com.j_fborges.online.usecase;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.j_fborges.online.domain.Consumer;
import br.com.j_fborges.online.exceptions.EntityNotFoundException;
import br.com.j_fborges.online.repository.IConsumerRepository;

@Service
public class FindConsumer {

private IConsumerRepository consumerRepository;
	
	@Autowired
	public FindConsumer(IConsumerRepository consumerRepository) {
		this.consumerRepository = consumerRepository;
	}
	
	public Page<Consumer> find(Pageable pageable) {
		return consumerRepository.findAll(pageable);
	}
	
	public Consumer findById(String id) {
		return consumerRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(Consumer.class, "id", id));
	}
	
	public Boolean isRegistered(String id) {
		Optional<Consumer> consumer = consumerRepository.findById(id);
		return consumer.isPresent() ? true : false;
	}

	public Consumer findByIdNumber(Long IdNumber) {
		return consumerRepository.findByIdNumber(IdNumber)
				.orElseThrow(() -> new EntityNotFoundException(Consumer.class, "IdNumber", String.valueOf(IdNumber)));
	}
}
