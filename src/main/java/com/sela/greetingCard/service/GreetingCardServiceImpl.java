package com.sela.greetingCard.service;

import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sela.Const;
import com.sela.greetingCard.repo.GreetingCardRepository;
import com.sela.model.GreetingCard;

@Service
public class GreetingCardServiceImpl implements GreetingCardService {

    private GreetingCardRepository greetingCardRepository;
    
    @PostConstruct
	public void init() {
    	
    	Optional<GreetingCard> existingDefaultCard = greetingCardRepository.findById(Const.DEFAULT_GREETING_ID);
    	if (!existingDefaultCard.isPresent()) {

			GreetingCard card = new GreetingCard();
			card.setId(Const.DEFAULT_GREETING_ID);
			card.setName("default template");
			card.setTemplate("Dear %s. We are so glad you are here. Yours, %s.");
			GreetingCard defaultCard = save(card);
			
			assertNotNull(card.getId());
			assertNotNull(defaultCard.getId());
    	}
	 }
	

	@Autowired
    public void setGreetingCardRepository(GreetingCardRepository GreetingCardRepository) {
        this.greetingCardRepository = GreetingCardRepository;
    }

	
    public GreetingCard save(GreetingCard greetingCard) {
    	greetingCard.setCreatedOn(new Date());
        return greetingCardRepository.save(greetingCard);
    }

    public void delete(GreetingCard greetingCard) {
        greetingCardRepository.delete(greetingCard);
    }

    public Optional<GreetingCard> findOne(String id) {
    	return greetingCardRepository.findById(id);
    }

    public Iterable<GreetingCard> findAll() {
        return greetingCardRepository.findAll();
    }


	@Override
	public void delete(String id) {
		greetingCardRepository.delete(greetingCardRepository.findById(id).get());
	}


	@Override
	public String populateTemplate(GreetingCard greetingCard, List<String> vars) {
		return String.format(greetingCard.getTemplate().toString(), vars.toArray(new Object[vars.size()]));
	}

}