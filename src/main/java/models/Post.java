package models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Post {
    public int userId;
    public int id;
    public String title;
    public String body;

    public Post() {
    }
}