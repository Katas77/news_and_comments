package com.example.NewsAndComments.web.dto.news;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpsertNewsRequest {
    @NotNull(message = "ID пользователя  должно быть указано")
    @Positive(message = "ID пользователя должно быть больше 0!")
    private Long user_id;


    @NotBlank(message = "поле категория   должно быть заполнено!")
    @Size(min = 3, max = 30, message = "поле категория не может быть меньше {min} и больше {max}!")
    private String category;


    @NotBlank(message = "поле новостей   должно быть заполнено!")
    @Size(min = 3, max = 300, message = "поле новостей не может быть меньше {min} и больше {max}!")
    private String text;

}
