package hexlet.code;


import hexlet.code.model.User;
import hexlet.code.repository.UserRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;

import java.util.Optional;


@SpringBootApplication
public class AppApplication {
	private static final Logger log = LoggerFactory.getLogger(AppApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}

//	@Bean
	public CommandLineRunner demo(UserRepository repository) {
		return (args) -> {
			repository.deleteAll();
			// save a few customers
			repository.save(new User("Jack", "Bauer","maile@inbox.ru", "12323213123"));
			repository.save(new User("Chloe", "O'Brian", "mailoo@inbox.ru", "12323213123"));
			repository.save(new User("Kim", "Bauer", "maibnme@inbox.ru", "12323213123"));
			repository.save(new User("David", "Palmer", "mailjhjhe@inbox.ru", "12323213123"));
			repository.save(new User("Michelle", "Dessler", "maijhhjjjjle@inbox.ru", "12323213123"));

			// fetch all customers
			log.info("Customers found with findAll():");
			log.info("-------------------------------");
			repository.findAll().forEach(customer -> {
				log.info(customer.toString());
			});
			log.info("");

			// fetch an individual customer by ID
			Optional<User> user = repository.findById(1L);
			log.info("Customer found with findById(1L):");
			log.info("--------------------------------");
			log.info(user.get().toString());
			log.info("");

			// fetch customers by last name
			log.info("Customer found with findByLastName('Bauer'):");
			log.info("--------------------------------------------");
			repository.findByLastName("Bauer").forEach(bauer -> {
				log.info(bauer.toString());
			});
			log.info("");
		};
	}
}
