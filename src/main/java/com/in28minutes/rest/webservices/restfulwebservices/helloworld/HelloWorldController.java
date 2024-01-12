package com.in28minutes.rest.webservices.restfulwebservices.helloworld;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

// REST API	
@RestController
public class HelloWorldController { 
	
	private MessageSource messageSource;
	
	public HelloWorldController(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/hello-world")
	public String helloWorld() {
		return "hello world";
	}
	
	@GetMapping(path = "/hello-world-bean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("Hello World");
	}
	
	// Path parameter eg: /users/{id}/todos/{id} => /users/12/todos/108
	@GetMapping(path = "/hello-world/path-variable/{name}")
	public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
		return new HelloWorldBean(String.format("Hello World, %s", name));
	}
	
	@GetMapping(path = "/hello-world-internationalized")
	public String helloWorldInternationalized() {
		// 1: en: English
		// 2: fr: French
		// For i18n to work two things are needed. 
		// 	  There must be a file at the same level of application.properties
		//    The file must be named messages.properties
		Locale locale = LocaleContextHolder.getLocale();
		return messageSource.getMessage("good.morning.message", null, "Default message", locale);		
	}
}
