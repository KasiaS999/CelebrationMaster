package com.example.celebration;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Główna klasa aplikacji Celebration.
 */
@EnableAspectJAutoProxy
@SpringBootApplication
public class CelebrationApplication {

	/**
	 * Metoda uruchamiająca aplikację Celebration.
	 *
	 * @param args argumenty wiersza poleceń
	 */
	public static void main(String[] args) {
		SpringApplication.run(CelebrationApplication.class, args);
	}


	/**
	 * Bean CommandLineRunner, który wykonuje początkowe działania po uruchomieniu aplikacji.
	 *
	 * @param repository    repozytorium CelebrationRepository
	 * @param mongoTemplate instancja MongoTemplate
	 * @return CommandLineRunner
	 */
	@Bean
	CommandLineRunner runner(CelebrationRepository repository, MongoTemplate mongoTemplate){
		return args -> {
			LocalDate date = LocalDate.of(2023, 8, 13);
			LocalDateTime datetime = date.atTime(18,00,00);
			MenuItem soup = new MenuItem("gazpacho", "cold tomato soup");
			MenuItem secondCourse = new MenuItem("chicken with potatoes");
			MenuItem secondVegan = new MenuItem("chilli sin carne");
			ShoppingItem peach = new ShoppingItem("peach", 3);
			ShoppingItem banana = new ShoppingItem("banana", 5);
			String eventName = "Anna's birthday";
			Celebration celebration = new Celebration(eventName,
					LocalDateTime.now(),
					"vegan guests",
					List.of(soup, secondCourse, secondVegan),
					List.of(peach, banana),
					List.of("John", "Kate", "Anna", "Mark"),
					"Food and board games"
			);

			repository.findCelebrationByEventName(eventName).ifPresentOrElse(celebration1 -> {
				System.out.println(eventName +" already exists");
			}, () -> {
				System.out.println("Inserting event" + eventName);
				repository.insert(celebration);
			});
		};
	}

}
