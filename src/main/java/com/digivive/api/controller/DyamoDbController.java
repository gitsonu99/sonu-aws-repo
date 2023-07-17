package com.digivive.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digivive.api.entity.Person;
import com.digivive.api.service.AwsBucketService;
import com.digivive.api.service.DynamoDbService;

@RestController
@RequestMapping("/person")
public class DyamoDbController {
	@Autowired 
	private DynamoDbService service;
	

@PostMapping("/save")
public Person savePerson(@RequestBody Person person) {
	return service.savePerson(person);
}
@PutMapping("/update")
public String updatePerson(@RequestBody Person person) {
	return service.updatePerson(person);
}

@GetMapping("/getById/{id}")
public Person getPersonById(@PathVariable("id")String id) {
	return service.findPersonById(id);
}
@DeleteMapping("/delete")
public String deletePerson(@RequestBody Person person) {
	return service.deletePerson(person);
}


@RequestMapping("/getMessage")
public String getMessage() {
	return "Welcome to amazon web service!";
}

@RequestMapping("/test")
public String testMessage() {
	return "Code has been modified!";
}




}
