package zxc.solevoy.openboard.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@Entity
@Table(name = "posts")
@Getter
@Setter
public class Post {

    @Id
    @Column(name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
        LocalDateTime now = LocalDateTime.now();

        long secondsDiff = ChronoUnit.SECONDS.between(publishedAt, now);
        long minutesDiff = ChronoUnit.MINUTES.between(publishedAt, now);
        long hoursDiff = ChronoUnit.HOURS.between(publishedAt, now);

        String formattedTime;
        if (secondsDiff < 5) {
            formattedTime = "только что";
        } else if (secondsDiff < 60) {
            formattedTime = String.format("%d секунд назад", secondsDiff);
        } else if (minutesDiff < 60) {
            formattedTime = String.format("%d минут назад", minutesDiff);
        } else if (hoursDiff < 24) {
            formattedTime = String.format("%d часов назад", hoursDiff);
        } else {
            formattedTime = publishedAt.format(DateTimeFormatter.ofPattern("HH:mm, dd MMMM, yyyy"));
        }

        return formattedTime;
    }
}
