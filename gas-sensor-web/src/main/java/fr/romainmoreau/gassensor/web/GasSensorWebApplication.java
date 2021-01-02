package fr.romainmoreau.gassensor.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.task.TaskExecutorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import fr.romainmoreau.gassensor.datamodel.GasSensorDataModel;

@SpringBootApplication
@EntityScan(basePackageClasses = GasSensorDataModel.class)
@ConfigurationPropertiesScan
@EnableScheduling
@EnableAsync
@EnableWebSocketMessageBroker
public class GasSensorWebApplication implements WebSocketMessageBrokerConfigurer {
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/stompEndpoint").withSockJS();
	}

	@Bean
	public TaskExecutor taskExecutor(TaskExecutorBuilder taskExecutorBuilder) {
		return taskExecutorBuilder.build();
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(GasSensorWebApplication.class, args);
	}
}
