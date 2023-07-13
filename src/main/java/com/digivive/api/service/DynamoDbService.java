package com.digivive.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digivive.api.dao.DynamoDbDao;
import com.digivive.api.entity.Person;

@Service
public class DynamoDbService {
@Autowired 
private DynamoDbDao  repository;

public Person savePerson(Person person) {
return 	repository.savePerson(person);
	
}

public String deletePerson(Person person) {
	return repository.deletePerson(person);
}

public String updatePerson(Person person) {
	return repository.updatePerson(person);
	
}

public Person findPersonById(String id) {
	return repository.findByid(id);
	
}
}
