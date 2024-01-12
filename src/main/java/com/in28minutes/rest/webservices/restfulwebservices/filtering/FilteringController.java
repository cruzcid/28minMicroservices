package com.in28minutes.rest.webservices.restfulwebservices.filtering;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.BeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {
	
	@GetMapping("/filtering")
	public MappingJacksonValue filtering() {
		SomeBean bean = new SomeBean("value1", "value2", "value3");
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(bean);
		
		SimpleBeanPropertyFilter simpleFilter = 
				SimpleBeanPropertyFilter.filterOutAllExcept("value1", "value3");
		
		// 'SomeBeanFilter' is the name that should be specified in @JsonFilter 
		//  at the bean in which the properties need to be filtered
		FilterProvider filters = 
				new SimpleFilterProvider().addFilter("SomeBeanFilter", simpleFilter);
		
		mappingJacksonValue.setFilters(filters);
		
		return mappingJacksonValue;
	}
	
	@GetMapping("/filtering-list")
	public MappingJacksonValue filteringList() {
		List<SomeBean> beanList = 
				Arrays.asList(new SomeBean("value1", "value2", "value3"),
							new SomeBean("value4", "value5", "value6"));
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(beanList);
		
		SimpleBeanPropertyFilter simpleFilter = 
				SimpleBeanPropertyFilter.filterOutAllExcept("value2");
		
		// 'SomeBeanFilter' is the name that should be specified in @JsonFilter 
		//  at the bean in which the properties need to be filtered
		FilterProvider filters = 
				new SimpleFilterProvider().addFilter("SomeBeanFilter", simpleFilter);
		
		mappingJacksonValue.setFilters(filters);
		
		return mappingJacksonValue;
	}
}
