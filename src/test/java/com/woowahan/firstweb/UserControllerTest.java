package com.woowahan.firstweb;

import com.woowahan.firstweb.user.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {
	private static final Logger log = LoggerFactory.getLogger(UserControllerTest.class);

	@Autowired
	private TestRestTemplate template;

	@Autowired
	private UserRepository userRepository;

	@Test
	public void createForm() {
		ResponseEntity<String> response = template.getForEntity("/users", String.class);
		log.debug(response.getBody());
	}

	@Test
	public void create() throws Exception {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.TEXT_HTML));
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
		params.add("userId", "javajigi");
		params.add("password", "pass");
		params.add("name", "재성");
		params.add("email", "javajigi@slipp.net");

		HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<MultiValueMap<String, Object>>(params, headers);
		ResponseEntity<String> response = template.postForEntity("/users", request, String.class);

		assertThat(response.getStatusCode(), is(HttpStatus.FOUND));
		assertThat(response.getHeaders().getLocation().getPath(), is("/"));
		assertNotNull(userRepository.findByUserId("javajigi"));
	}
}
