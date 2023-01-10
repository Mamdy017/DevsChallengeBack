package DevsChallenge.example.DevsChallenge;

import DevsChallenge.example.DevsChallenge.Repositories.RolesRepostory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DevsChallengeApplication implements CommandLineRunner {

	private final RolesRepostory rolesRepostory;

	public DevsChallengeApplication(RolesRepostory rolesRepostory) {
		this.rolesRepostory = rolesRepostory;
	}

	public static void main(String[] args) {
		SpringApplication.run(DevsChallengeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if (rolesRepostory.findAll().size()==0){
			rolesRepostory.create();
		}

	}
}
