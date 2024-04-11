/*
 * 
 */
package com.jeff.puc.dto;

import com.jeff.puc.domain.Person;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// TODO: Auto-generated Javadoc
/**
 * The Class PersonDTO.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonDTO {
   
   /** The id. */
   protected Long id;
   
   /** The name. */
   protected String name;
   
   /** The phone. */
   protected String phone;
   
   /** The email. */
   protected String email;
   
   /** The cep. */
   protected String cep;
   
   /** The street. */
   protected String street;
   
   /** The number. */
   protected Integer number;
   
   /** The district. */
   protected String district;
   
   /** The city. */
   protected String city;
   
   /** The state. */
   protected String state;
   
   /** The country. */
   protected String country;

   /**
    * Instantiates a new person DTO.
    *
    * @param person the person
    */
   public PersonDTO(Person person) {
        this.id = person.getId();
        this.name = person.getName();
        this.phone = person.getPhone();
        this.email = person.getEmail();
        this.cep = person.getCep();
        this.street = person.getStreet();
        this.number = person.getNumber();
        this.district = person.getDistrict();
        this.city = person.getCity();
        this.state = person.getState();
        this.country = person.getCountry();
   }
}
