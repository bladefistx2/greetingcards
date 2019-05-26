package com.sela.greetingCard.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sela.greetingCard.service.GreetingCardService;
import com.sela.model.GreetingCard;



@Controller
public class GreetingCardController {
	
	@Autowired
	private GreetingCardService greetingCardService;

    @GetMapping("/greetingCard/catalog")
    @ResponseBody
    public Object index() {
    	ArrayList<GreetingCard> result = new ArrayList<GreetingCard>();
        for (GreetingCard card : greetingCardService.findAll()) {
        	result.add(card);
        }
        return result;
    }
    
    @GetMapping("greetingCard/card/{id}")
    @ResponseBody
    public Object get(@PathVariable String id) {
    	Optional<GreetingCard> card = greetingCardService.findOne(id);
		if (!card.isPresent()) {
			return ResponseEntity.notFound().build();
		}
        return card.get();
    }
    
    @PostMapping("greetingCard/card/{id}")
    @ResponseBody
    public Object createGreeting(@PathVariable String id, @RequestBody ArrayList<String> arguments) {
    	Optional<GreetingCard> card = greetingCardService.findOne(id);
		if (!card.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		String res;
		try {
			res = greetingCardService.populateTemplate(card.get(), arguments);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return ResponseEntity.ok("Wrong number of parameters. Format should be: " + card.get().getTemplate());
		}
				
        return ResponseEntity.ok(res);
    }
    
    @PutMapping("greetingCard/card/")
    @ResponseBody
    public Object addGreetingCardTemplate(@RequestBody GreetingCard cardTemplate) {
	    return ResponseEntity.ok(greetingCardService.save(cardTemplate)).getBody();
    }
    
    
}