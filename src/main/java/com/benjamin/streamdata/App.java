package com.benjamin.streamdata;

import com.benjamin.streamdata.core.domain.services.ProductService;
import com.benjamin.streamdata.infraestructure.adapters.ProductRepositoryAdapter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;

@Log4j2
@Component
@SpringBootApplication
public class App {

	@Autowired
	private ProductService service;
	@Autowired
	private ProductRepositoryAdapter repository;

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
		log.info("Staring app!!!");


	}

	@EventListener(ApplicationReadyEvent.class)
	public void ready(){
		Instant startStream = Instant.now();

		log.info("Starting to get sku by stream");
		var count = this.service.getStream().count();
		Instant stopStream = Instant.now();
		Duration streamDuration = Duration.between( startStream , stopStream );
		log.info("Total SKU: {} retrieved by streams Time: {}", count, streamDuration.toMillis());

		Instant startSql = Instant.now();
		var products = this.repository.findAll();
		Instant stopSql = Instant.now();
		Duration sqlDuration = Duration.between( startSql , stopSql );

		log.info("Total SKU: {} retrieved by a big sql query Time: {}", products.size(), sqlDuration.toMillis());

	}

}
