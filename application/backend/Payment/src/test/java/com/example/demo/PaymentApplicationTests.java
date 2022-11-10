package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;

import com.example.demo.infrastructure.PaymentRepository;
import com.example.demo.infrastructure.external.Message;
import com.example.demo.presentation.PaymentController;
import com.example.demo.presentation.RequestDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

@ActiveProfiles("test")
@EmbeddedKafka(partitions = 1, brokerProperties = { "listeners=PLAINTEXT://localhost:9092", "port=9092" })
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class PaymentApplicationTests {

	@Autowired
	private PaymentController paymentController;

	@Autowired
	private RestTemplate restTemplate;

	private MockMvc mockMvc;

	private MockRestServiceServer mockServer;

	private BlockingQueue<String> queue;

	@BeforeEach
	void setup() {
		this.mockServer = MockRestServiceServer.bindTo(restTemplate).build();
		this.mockMvc = MockMvcBuilders.standaloneSetup(paymentController).build();
		this.queue = new LinkedBlockingQueue<>();
	}

	@Test
	public void postPayment_201() throws Exception {
		String mockResponse = "{\"id\":1,\"name\":\"牛丼\",\"price\":300,\"createdAt\":\"2022-04-27T08:52:46.349+00:00\",\"tuition\":{\"cal\":829.0,\"protein\":78.4,\"fat\":15.5,\"carbo\":3.3,\"salt\":9.0}}";
		mockServer.expect(requestTo("http://menu/1")).andRespond(withSuccess(mockResponse, MediaType.APPLICATION_JSON));
		RequestDTO requestBody = new RequestDTO("test_user", 1);
		String requrstJson = new ObjectMapper().writeValueAsString(requestBody);
		MvcResult mvcResult = mockMvc
				.perform(MockMvcRequestBuilders.post("/").contentType(MediaType.APPLICATION_JSON).content(requrstJson))
				.andReturn();
		String payment = this.queue.poll(10, TimeUnit.SECONDS);
		Message actual = new ObjectMapper().readValue(payment, Message.class);
		assertEquals(201, mvcResult.getResponse().getStatus());
		assertEquals(requestBody.getMenuId(), actual.getMenuId());
		assertEquals(requestBody.getEmployee(), actual.getEmployee());
	}

	@Test
	public void postPayment_400() throws Exception {
		String mockResponse = "{\"id\":1,\"name\":\"牛丼\",\"price\":300,\"createdAt\":\"2022-04-27T08:52:46.349+00:00\",\"tuition\":{\"cal\":829.0,\"protein\":78.4,\"fat\":15.5,\"carbo\":3.3,\"salt\":9.0}}";
		mockServer.expect(requestTo("http://menu/1")).andRespond(withSuccess(mockResponse, MediaType.APPLICATION_JSON));
		String requestBody = "{\"employee1\":\"test\", \"menuId\":\"1\"}";
		String requrstJson = new ObjectMapper().writeValueAsString(requestBody);
		MvcResult mvcResult = mockMvc
				.perform(MockMvcRequestBuilders.post("/").contentType(MediaType.APPLICATION_JSON).content(requrstJson))
				.andReturn();
		assertEquals(400, mvcResult.getResponse().getStatus());
	}

	@Test
	public void postPayment_500_DBError() throws Exception {
		PaymentRepository paymentRepository = mock(PaymentRepository.class);
		doThrow(new RuntimeException()).when(paymentRepository).save(any());
		String mockResponse = "{\"id\":1,\"name\":\"牛丼\",\"price\":300,\"createdAt\":\"2022-04-27T08:52:46.349+00:00\",\"tuition\":{\"cal\":829.0,\"protein\":78.4,\"fat\":15.5,\"carbo\":3.3,\"salt\":9.0}}";
		mockServer.expect(requestTo("http://menu/1")).andRespond(withSuccess(mockResponse, MediaType.APPLICATION_JSON));
		RequestDTO requestBody = new RequestDTO("test_user", 1);
		String requrstJson = new ObjectMapper().writeValueAsString(requestBody);
		MvcResult mvcResult = mockMvc
				.perform(MockMvcRequestBuilders.post("/").contentType(MediaType.APPLICATION_JSON).content(requrstJson))
				.andReturn();
		String payment = this.queue.poll(10, TimeUnit.SECONDS);
		assertNull(payment);
		assertEquals(500, mvcResult.getResponse().getStatus());
	}

//	@Test
//	public void postPayment_500_MenuNotFound() throws Exception {
//		fail();
//	}
//
//	@Test
//	public void postPayment_500_BadRequestFromMenu() throws Exception {
//		fail();
//	}
//
//	@Test
//	public void postPayment_500_MenuDown() throws Exception {
//		fail();
//	}
//
	@Test
	public void getPayment_200() {
		fail();
	}

	@Test
	public void getPayment_404_PaymentNotFound() {
		fail();
	}
//
//	@Test
//	public void listPayment_200() {
//		fail();
//	}

	@KafkaListener(topics = "${app.topic.payment}")
	public void consumer(String payment) {
		this.queue.add(payment);
	}

}
