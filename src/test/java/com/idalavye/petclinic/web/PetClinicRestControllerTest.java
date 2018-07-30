package com.idalavye.petclinic.web;

import com.idalavye.petclinic.model.Owner;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Array;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PetClinicRestControllerTest {

    private RestTemplate restTemplate;

    @Before //Bir nevi constructor gibi Test Controller çağrıldığı zaman initialize edilir.
    public void setUp(){
        restTemplate = new RestTemplate();
        BasicAuthorizationInterceptor basicAuthorizationInterceptor =  new BasicAuthorizationInterceptor("user","secret");
        restTemplate.setInterceptors(Arrays.asList(basicAuthorizationInterceptor));
    }

    @Test
    public void testGetOwnerById(){
        ResponseEntity<Owner> responseEntity = restTemplate.getForEntity("http://localhost:8080/petclinic/rest/owner/1",Owner.class);

        MatcherAssert.assertThat(responseEntity.getStatusCodeValue(), Matchers.equalTo(200));
        //MatcherAssert.assertThat(responseEntity.getBody().getLastName(),Matchers.equalTo("Dagdelen"));
    }

    @Test
    public void testGetOwnersByLastName(){
        ResponseEntity<List> responseEntity = restTemplate.getForEntity("http://localhost:8080/petclinic/rest/owner?ln=Dagdelen",List.class);

        MatcherAssert.assertThat(responseEntity.getStatusCodeValue(),Matchers.equalTo(200));
        List<Map<String,String>> body = responseEntity.getBody();
        //keyler firstName,lastName  value'ler İbrahim,Dagdelen
        List<String> firstNames = body.stream().map(e->e.get("firstName")).collect(Collectors.toList());
        MatcherAssert.assertThat(firstNames,Matchers.containsInAnyOrder("İbrahim"));
    }

    @Test
    public void testGetOwners(){
        ResponseEntity<List> responseEntity = restTemplate.getForEntity("http://localhost:8080/petclinic/rest/owners",List.class);

        MatcherAssert.assertThat(responseEntity.getStatusCodeValue(),Matchers.equalTo(200));
        List<Map<String,String>> body = responseEntity.getBody();
        //keyler firstName,lastName  value'ler İbrahim,Dagdelen
        List<String> firstNames = body.stream().map(e->e.get("firstName")).collect(Collectors.toList());
        MatcherAssert.assertThat(firstNames,Matchers.containsInAnyOrder("İbrahim","Ali","Hasan","Humeyra"));
    }


    @Test
    public void testCreateOwner(){
        Owner owner = new Owner();
        owner.setFirstName("Mustafa");
        owner.setLastName("Yaylalı");

        //postForLocation bize geri dönüş değeri olarak bir URI döner
        URI location = restTemplate.postForLocation("http://localhost:8080/petclinic/rest/owner",owner);

        Owner owner1 = restTemplate.getForObject(location,Owner.class);

        MatcherAssert.assertThat(owner1.getFirstName(),Matchers.equalTo(owner.getFirstName()));
        MatcherAssert.assertThat(owner1.getLastName(),Matchers.equalTo(owner.getLastName()));

    }

    @Test
    public void testUpdateOwner(){
        Owner owner = restTemplate.getForObject("http://localhost:8080/petclinic/rest/owner/1",Owner.class);

        MatcherAssert.assertThat(owner.getFirstName(),Matchers.equalTo("İbrahim"));

        owner.setFirstName("Ali");
        restTemplate.put("http://localhost:8080/petclinic/rest/owner/1",owner);

        owner = restTemplate.getForObject("http://localhost:8080/petclinic/rest/owner/1",Owner.class);
        MatcherAssert.assertThat(owner.getFirstName(),Matchers.equalTo("Ali"));
    }

    @Test
    public void deleteOwner(){
        restTemplate.delete("http://localhost:8080/petclinic/rest/owner/1");

        try {
            restTemplate.getForEntity("http://localhost:8080/petclinic/rest/owner/1",Owner.class);
            Assert.fail("should have not returned owner");
        }catch (HttpClientErrorException e){ //HttpStatus ile çalıştığımız zaman RestClientException yerine HttpClientErrorException sınıfını kullanırız
            MatcherAssert.assertThat(e.getStatusCode().value(),Matchers.equalTo(404));
        }
    }

}
