package com.sela.greetingCard.service;

import java.util.List;
import java.util.Optional;

import com.sela.model.GreetingCard;

public interface GreetingCardService {

    GreetingCard save(GreetingCard gCard);

    void delete(String id);

    Optional<GreetingCard> findOne(String id);

    Iterable<GreetingCard> findAll();

	String populateTemplate(GreetingCard greetingCard, List<String> vars);

}
