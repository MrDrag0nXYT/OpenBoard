package zxc.solevoy.openboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zxc.solevoy.openboard.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

    void deletePostById(Long id);
    Post findPostById(Long id);
}
