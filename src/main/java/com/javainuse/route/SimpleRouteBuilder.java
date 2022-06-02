package com.javainuse.route;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;

import com.javainuse.model.Client;
import com.javainuse.processor.CreateClientProcessor;
import com.javainuse.processor.MyProcessor;

public class SimpleRouteBuilder extends RouteBuilder {

    JacksonDataFormat jsonDataFormat = new JacksonDataFormat(Client.class);

    @Override
    public void configure() throws Exception {

        // route for REST GET Call
        from("file:C:/inputFolderREST?noop=true").setHeader(Exchange.HTTP_METHOD, simple("GET"))
                .to("http://localhost:3000/client").process(new MyProcessor());

        // route for REST POST Call
        from("file:C:/inboxPOST?noop=true").process(new CreateClientProcessor()).marshal(jsonDataFormat)
                .setHeader(Exchange.HTTP_METHOD, simple("POST"))
                .setHeader(Exchange.CONTENT_TYPE, constant("application/json")).to("http://localhost:3000/client")
                .process(new MyProcessor());
    }

}
