package egovframework.test.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@RestController
public class TestController {

    private final WebClient webClient;

    // FastAPI 서버와 연동할 WebClient 설정 (Base URL: http://localhost:8000)
    public TestController(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8000").build();
    }

    // 연동 테스트 엔드포인트: React → SpringBoot → Python
    @GetMapping("/test")
    public Mono<String> test() {
        return webClient.get()
                .uri("/test")
                .retrieve()
                .bodyToMono(String.class)
                .map(response -> "React → SpringBoot → Python 연동 테스트 성공: " + response);
    }

    // 연동 테스트 엔드포인트: React → SpringBoot
    @GetMapping("/test2")
    public String test2() {
        return "React → SpringBoot 연동 테스트 성공!";
    }
}

