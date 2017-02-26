package fr.romainmoreau.gassensor.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@SpringBootApplication
@EnableWebSocketMessageBroker
public class GasSensorWebApplication extends AbstractWebSocketMessageBrokerConfigurer {
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/stompEndpoint").withSockJS();
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(GasSensorWebApplication.class, args);
	}
}
