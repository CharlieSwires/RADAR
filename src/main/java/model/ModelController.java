package main.java.model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication(scanBasePackages = "model")
public class ModelController {
	@Autowired
	private ModelService modelService;


	//Fetches users
	@GetMapping("/Tracks")
	public ResponseEntity<Tracks> getUsers() {
		System.out.println("Get RADAR/Tracks");
		Tracks ob = modelService.getTracks();
		return new ResponseEntity<Tracks>(ob, HttpStatus.OK);
	}


} 