package pt.nos.ms.something;

import java.time.LocalDateTime;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pt.nos.ms.something.dao.SomethingDAO;
import pt.nos.ms.something.repository.SomethingRepository;

@SpringBootApplication
public class GetSomethingApplication {

	public static void main(String[] args) {
		SpringApplication.run(GetSomethingApplication.class, args);
	}


	@Bean
	public CommandLineRunner demo(SomethingRepository repo) {
		return (args) -> {
			SomethingDAO s1 = new SomethingDAO();
			s1.setOneName("one");
			s1.setAnotherName("something");
			s1.setTimestamp(LocalDateTime.now());
			s1.setCreatedAt(LocalDateTime.now());
			s1.setCreatedBy("baseline");
			s1.setUpdatedAt(LocalDateTime.now());
			s1.setUpdatedBy("baseline");

			SomethingDAO s2 = new SomethingDAO();
			s2.setOneName("other");
			s2.setAnotherName("something");
			s2.setTimestamp(LocalDateTime.now());
			s2.setCreatedAt(LocalDateTime.now());
			s2.setCreatedBy("baseline");
			s2.setUpdatedAt(LocalDateTime.now());
			s2.setUpdatedBy("baseline");
			
			SomethingDAO s3 = new SomethingDAO();
			s3.setOneName("another");
			s3.setAnotherName("something");
			s3.setTimestamp(LocalDateTime.now());
			s3.setCreatedAt(LocalDateTime.now());
			s3.setCreatedBy("baseline");
			s3.setUpdatedAt(LocalDateTime.now());
			s3.setUpdatedBy("baseline");

			
			SomethingDAO s4 = new SomethingDAO();
			s4.setOneName("oneother");
			s4.setAnotherName("something");
			s4.setTimestamp(LocalDateTime.now());
			s4.setCreatedAt(LocalDateTime.now());
			s4.setCreatedBy("baseline");
			s4.setUpdatedAt(LocalDateTime.now());
			s4.setUpdatedBy("baseline");

			repo.save(s1);
			repo.save(s2);
			repo.save(s3);
			repo.save(s4);
		};
	}
	

}
