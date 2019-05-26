package com.sela.greetingCard;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.SortedSet;
import java.util.TreeSet;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.sela.greetingCard.MyApplication;
import com.sela.greetingCard.service.GreetingCardService;
import com.sela.model.GreetingCard;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = MyApplication.class)
public class MyApplicationTests {
	
   	@Autowired
    private GreetingCardService greetingCardService;

    @Autowired
    private ElasticsearchTemplate esTemplate;
    
    @Before
    public void before() {
        esTemplate.deleteIndex(GreetingCard.class);
        esTemplate.createIndex(GreetingCard.class);
        esTemplate.putMapping(GreetingCard.class);
        esTemplate.refresh(GreetingCard.class);
    }
    
    @Test
    public void testSave() {

    	GreetingCard g = new GreetingCard("1", "test", "Welcome to sela group, $1", new Date());
    	GreetingCard test = greetingCardService.save(g);

        assertNotNull(g.getId());
        assertNotNull(test.getId());

    }

	@Test
	public void contextLoads() {
	}

}
