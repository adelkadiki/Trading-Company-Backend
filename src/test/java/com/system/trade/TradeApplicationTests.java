package com.system.trade;

import com.system.trade.model.Client;
import com.system.trade.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TradeApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TradeApplicationTests {


	@Autowired
	UserService userService;

//	@Test
//	void contextLoads() {
//	}

    @Autowired
	private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

	@Test
	public void getAllClients(){


		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange("http://localhost:"+port+"/allclients", HttpMethod.GET, entity, String.class);
		assertNotNull(response.getBody());

	}

	@Test
	public void addClientTest(){

		Client client = new Client();
		client.setContact("client");
		client.setAddress("russa");
		client.setCompany("wahda");
		client.setPhone("09-998-73874");

			ResponseEntity<Client> postResponse = restTemplate.postForEntity("http://localhost:"+port+"/addclient",
					client, Client.class);
		assertNotNull(postResponse);
		assertNotNull(postResponse.getBody());

	}

	@Test
	public void getClientByIdTest(){

		Client client = restTemplate.getForObject("http://localhost:"+port+"/findclientbyid/1", Client.class);
		assertNotNull(client);
	}

	@Test
	public void updateClientTest(){
		Client client = restTemplate.getForObject("http://localhost:"+port+"/findclientbyid/4", Client.class);
			client.setContact("upated client");
			client.setCompany("wahda company");
			 restTemplate.put("http://localhost:"+port+"/updateclient", client);
		     //Client updatedClient= restTemplate.getForObject("http://localhost/findclientbyid/4", Client.class);
			//assertNotNull(updatedClient);
		Client updatedClient = restTemplate.getForObject("http://localhost:"+port+"/findclientbyid/4", Client.class);
		assertNotNull(updatedClient);
	}

	@Test
	public void deleteCleintTest(){

		Client client = restTemplate.getForObject("http://localhost:"+port+"/findclientbyid/102", Client.class);
		assertNotNull(client);

		restTemplate.delete("http://localhost:"+port+"/deleteclient/102", Client.class);
		try {
			client = restTemplate.getForObject("http://localhost:"+port+"/findclientbyid/102", Client.class);
		} catch (final HttpClientErrorException e) {

			assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
		}
	}


}
