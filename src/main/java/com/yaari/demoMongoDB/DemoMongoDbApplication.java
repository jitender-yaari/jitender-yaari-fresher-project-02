package com.yaari.demoMongoDB;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Queue;

@SpringBootApplication
public class DemoMongoDbApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoMongoDbApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(StudentRepository repository, MongoTemplate mongoTemplate){
		return args -> {
			Address address=new Address(
				"India",
					"Mumbai",
					"123456"
			);
			String email ="rahul@gamil.com";
			Student student = new Student(
					"Rahul",
					"Kumar",
					email,
					Gender.MALE,
					address,
					List.of("Computer Science"),
					BigDecimal.TEN,
					LocalDateTime.now()
			);

			repository.findStudentByEmail(email).ifPresentOrElse(s -> {

				System.out.println(s +" already taken");

			}, ()-> {
				System.out.println("Inserting students "+student);
				repository.insert(student);
			});

			/*Query query = new Query();
			query.addCriteria(Criteria.where("email").is(email));
			List<Student> students =mongoTemplate.find(query, Student.class);
			if (students.size()>1){
				throw new IllegalStateException("the email is already taken many times "+ email);
			}
			if (students.isEmpty()) {
				System.out.println("Inserting students "+student);
				repository.insert(student);
			}else{
				System.out.println(student +" already taken");
			}*/
		};
	}

}
