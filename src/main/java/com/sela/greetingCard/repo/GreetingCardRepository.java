package com.sela.greetingCard.repo;

import java.util.Optional;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.sela.model.GreetingCard;


public interface GreetingCardRepository extends ElasticsearchRepository<GreetingCard, String> {

    Optional<GreetingCard> findById(String id);

}