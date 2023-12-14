package zxc.solevoy.openboard.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "posts")
@Getter
@Setter
public class Post {

    @Id
    @Column(name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "content", length = 4096)
    private String content;

    @Column(name = "published_at")
    private LocalDateTime publishedAt;

    @Transient
    private String publishedAtFormatted;

    @Column(name = "edited_at")
    private LocalDateTime editedAt;

    @Column(name = "is_edited")
    private Boolean isEdited = false;


    public String getPublishedAtFormatted() {

        if (Duration.between(publishedAt, LocalDateTime.now()).getSeconds() < 60) {
            return DateTimeFormatter.ofPattern("ss").format(publishedAt) + " секунд назад";
        } else if (Duration.between(publishedAt, LocalDateTime.now()).getSeconds() >= 60
                && Duration.between(publishedAt, LocalDateTime.now()).getSeconds() < 3599) {
            return DateTimeFormatter.ofPattern("mm").format(publishedAt) + " минут назад";
        } else if (Duration.between(publishedAt, LocalDateTime.now()).getSeconds() >= 3599
                && Duration.between(publishedAt, LocalDateTime.now()).getSeconds() < 86459){
            return DateTimeFormatter.ofPattern("HH").format(publishedAt) + " часов назад";
        }

        return DateTimeFormatter.ofPattern("HH:mm, dd MMMM, yyyy").format(publishedAt);
    }
}
