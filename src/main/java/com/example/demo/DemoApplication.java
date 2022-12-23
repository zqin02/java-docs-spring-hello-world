package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;

@SpringBootApplication
@RestController
public class DemoApplication {
	private static final Logger logger = LoggerFactory.getLogger(DemoApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@RequestMapping("/")
	String sayHello() {

		logger.info("test test test 123");
		return "Hello World!";
	}


	@RequestMapping("/file")
	String createFiles(@RequestBody(required = false) Map<String,String> mapBody) {
		String location = "/logs/"+System.currentTimeMillis()+".txt";
		if(mapBody!=null) {
			 location = mapBody.get("filepath");
		}
		try {
			FileWriter myWriter = new FileWriter(location);
			myWriter.write("Ftest file share");
			myWriter.close();
			logger.info("Successfully wrote to the file.");
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		logger.info("test test test 123 create file");
		return "File created!";
	}
}
