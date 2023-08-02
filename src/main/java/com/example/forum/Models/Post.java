package com.example.forum.Models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class Post {
    private int id;
    @NotEmpty(message = "Title should not be empty")
    @Size(min=2, max=100, message = "Title should be between 2 and 100 characters")
    private String title;

    @NotEmpty(message = "Title should not be empty")
    private String text;
    private LocalDateTime created_on;
    private DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    private String formattedDate;

    public void setCreated_on(LocalDateTime created_on){
        this.created_on = created_on;
        formattedDate = created_on.format(myFormatObj);
    }

}
