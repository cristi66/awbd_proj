package com.awbd;

import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ContextLoadTests {
    
    @Test
    public void textXmlContext(){

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContexnt.xml");

        
    }

}
