package com.example.demo.Config;

import java.security.Principal;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import com.example.demo.Model.Freight;
import com.example.demo.Model.Transporter;
import com.example.demo.Repository.FreightRepository;
import com.example.demo.Repository.TransporterRepository;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfiguration extends AbstractWebSocketMessageBrokerConfigurer{
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/socket")
                .setAllowedOrigins("*")
                .setHandshakeHandler(new RandomUsers())
                .withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/app")
                .enableSimpleBroker("/topic","/queue");
//                .setRelayHost("localhost")
//                .setRelayPort(61613)
//                .setClientLogin("guest")
//                .setClientPasscode("guest");
    }
    
    private class RandomUsers extends DefaultHandshakeHandler {
    	
    	@Autowired
    	FreightRepository freightRepository;
    	
    	@Autowired
    	TransporterRepository transporterRepository;
    	
    	@Override
    	protected Principal determineUser(ServerHttpRequest request,WebSocketHandler wsHandler,
    			Map<String,Object> attributes) {
    		ServletServerHttpRequest s_request=(ServletServerHttpRequest ) request;
    		System.out.println(s_request.getServletRequest().getParameter("id"));
//    		System.out.println(s_request.getServletRequest().getAttribute("id"));
    		System.out.println(wsHandler);
    		System.out.println(attributes);
//    		long id=Long.parseLong(s_request.getServletRequest().getParameter("id"));
    		String id=s_request.getServletRequest().getParameter("id");
    		System.out.println("id : "+id);
//    		if(id.charAt(0)=='t')
//    		Freight fr=freightRepository.findById(d);
//    		Transporter tr=transporterRepository.findById(id);
    		return new UsernamePasswordAuthenticationToken(id,null);
//    		return new UsernamePasswordAuthenticationToken(UUID.randomUUID(),null);
    	}
    	
    } 
}

