package com.benjamin.streamdata;

import com.benjamin.streamdata.core.application.ProductUseCases;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;


@Log4j2
@Component
@SpringBootApplication
public class App {

	@Autowired
	private ProductUseCases productUseCases;

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void onReady() {
		productUseCases.groupByDepartment604Brand().get("No brand").forEach(p -> log.info(p));

		//log.info("Value: {}", value);
		/*productUseCases
				.getSkuWithoutStock()
				.forEach(product -> log.info("Product {}", product));*/
	}

}
