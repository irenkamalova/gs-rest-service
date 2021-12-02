package com.example.restservice;

import com.google.common.util.concurrent.RateLimiter;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();
	private final RateLimiter rateLimiter = RateLimiter.create(0.1);

	@GetMapping("/greeting")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		rateLimiter.acquire();
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}
}
