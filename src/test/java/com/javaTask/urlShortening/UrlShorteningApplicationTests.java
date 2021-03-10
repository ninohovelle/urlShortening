package com.javaTask.urlShortening;
import static org.assertj.core.api.Assertions.assertThat;
import com.javaTask.urlShortening.controller.UrlShorteningController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UrlShorteningApplicationTests {
	@Autowired
	private UrlShorteningController ulShorteningController;

	@Test
	void contextLoads() throws Exception {
		assertThat(ulShorteningController).isNotNull();
	}

}
