package com.example.springboottwoapps;

import com.example.springboottwoapps.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;

/**
 * Створіть ще один застосунок на Spring Boot, розгорніть його локально, але на іншому порту (наприклад, server.port=8099).
 * Під'єднайте його до вашої схеми БД. Додайте сутність Task та кінцеву точку для отримання задачі за айді.
 * Зверніться з першого застосунку до другого за цією кінцевою точкою та отримайте співробітника за допомогою другого застосунку.
 */

@SpringBootApplication
public class SpringBootTwoAppsApplication implements CommandLineRunner {
    @Autowired
    WebClient webClient;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootTwoAppsApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        var response = webClient.get()
                .uri("http://localhost:8099/tasks/2")
                .retrieve()
                .bodyToMono(Task.class)
                .map(task -> {
                    task.setCreatedAt(LocalDateTime.now());
                    return task;
                })
                .block();

        System.out.println(response);

    }

}
