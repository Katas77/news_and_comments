package com.example.NewsAndComments.web.dto.comment;


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
public class UpsertCommentRequest {
    @NotNull(message = "ID пользователя  должно быть указано")
    @Positive(message = "ID пользователя должно быть больше 0!")
    private Long user_id;

    @NotNull(message = "ID новости  должно быть указано")
    @Positive(message = "ID новости должно быть больше 0!")
    private Long news_id;


    @NotBlank(message = "поле коментарий    должно быть заполнено!")
    @Size(min = 3, max = 80, message = "поле коментарий  не может быть меньше {min} и больше {max}!")
    private String text;

}
