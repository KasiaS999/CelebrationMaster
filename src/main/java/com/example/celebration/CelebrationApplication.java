package com.example.celebration;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class CelebrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(CelebrationApplication.class, args);
	}

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

//			usingMongoTemplate(repository, mongoTemplate, eventName, celebration);
			repository.findCelebrationByEventName(eventName).ifPresentOrElse(celebration1 -> {
				System.out.println(eventName +" already exists");
			}, () -> {
				System.out.println("Inserting event" + eventName);
				repository.insert(celebration);
			});
		};
	}

	private static void usingMongoTemplate(CelebrationRepository repository, MongoTemplate mongoTemplate,
										   String eventName, Celebration celebration) {
		Query query = new Query();
		query.addCriteria(Criteria.where("eventName").is(eventName));
		List<Celebration> celebrations = mongoTemplate.find(query, Celebration.class);

		if (celebrations.size() > 1){
			throw new IllegalStateException("found many events with this name" + eventName);
		}

		if (celebrations.isEmpty()){
			System.out.println("Inserting event" + eventName);
			repository.insert(celebration);
		}
		else{
			System.out.println(eventName +" already exists");
		}
	}
}
