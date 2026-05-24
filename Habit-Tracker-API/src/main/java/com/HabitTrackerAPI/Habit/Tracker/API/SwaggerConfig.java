package com.HabitTrackerAPI.Habit.Tracker.API;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI habitTrackerOpenAPI() {

        return new OpenAPI()
                .info(new Info()
                        .title("Habit Tracker API")
                        .description(
                                "REST API for managing daily habits, streaks, reminders and history")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Aryan Patil")
                                .email("your-email@gmail.com")
                                .url("https://github.com/yourgithub")))
                .externalDocs(new ExternalDocumentation()
                        .description("Project GitHub Repository")
                        .url("https://github.com/yourgithub/Habit-Tracker-API"));
    }
}