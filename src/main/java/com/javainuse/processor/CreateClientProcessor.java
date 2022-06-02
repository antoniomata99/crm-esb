package com.javainuse.processor;

import com.javainuse.model.Client;
import org.apache.camel.Processor;

import org.apache.camel.Exchange;

public class CreateClientProcessor implements Processor{

    public void process(Exchange exchange)  throws Exception {
        System.out.println(exchange.getIn().getBody(String.class));
        Client cli = new Client();
        cli.setFullname("Andrés");
        cli.setEmail("agarciaca@unbosque.edu.co");
        cli.setPhone("3011111111");
        exchange.getIn().setBody(cli);
    }
}
