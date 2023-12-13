package zxc.solevoy.openboard.service.implementation;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import zxc.solevoy.openboard.entity.Post;
import zxc.solevoy.openboard.repository.PostRepository;
import zxc.solevoy.openboard.service.PostService;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
@Primary
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public void createPost(Post post) {
        post.setPublishedAt(LocalDateTime.now());
        postRepository.save(post);
    }

    @Override
    public void updatePost(Post post) {
        postRepository.save(post);
    }

    @Override
    public void editPost(Post post) {
        post.setEditedAt(LocalDateTime.now());
        post.setIsEdited(true);
        postRepository.save(post);
    }

    @Override
    public void deletePost(Long id) {
        postRepository.deletePostById(id);
    }

    @Override
    public Post findPostById(Long id) {
        return postRepository.findPostById(id);
    }
}
