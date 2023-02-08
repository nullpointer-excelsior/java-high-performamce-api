package com.benjamin.streamdata;

import com.benjamin.streamdata.core.application.StreamProductUseCases;
import com.benjamin.streamdata.core.domain.model.Product;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;


@Log4j2
@Component
@SpringBootApplication
public class App {

	@Autowired
	private StreamProductUseCases productUseCases;

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void onReady() {
		var product = Product.builder().build();
		//productUseCases.groupByDepartment604Brand().get("No brand").forEach(p -> log.info(p));

		//log.info("Value: {}", value);
		/*productUseCases
				.getSkuWithoutStock()
				.forEach(product -> log.info("Product {}", product));*/
	}

}
