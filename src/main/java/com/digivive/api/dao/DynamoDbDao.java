package com.digivive.api.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.digivive.api.entity.Person;

@Repository
public class DynamoDbDao {
@Autowired
private DynamoDBMapper mapper;

public Person savePerson(Person person) {
	mapper.save(person);
	return person;
}
public String deletePerson(Person person) {
	mapper.delete(person);
	return "Person has been deleted sucessfully!";
}

public Person findByid(String pid) {
	return mapper.load(Person.class,pid);
}


public  String updatePerson(Person person) {
    mapper.save(person, getSaveExpression(person));
    return "Record updated sucessfully!";
}

private DynamoDBSaveExpression getSaveExpression(Person person) {
    DynamoDBSaveExpression saveExpression = new DynamoDBSaveExpression();
    Map<String , ExpectedAttributeValue> map = new HashMap<String, ExpectedAttributeValue>();
       map.put("personId", new ExpectedAttributeValue(new AttributeValue().withS(person.getPersonId())));
       saveExpression.withExpected(map)  ; 

    return saveExpression;
}


}
