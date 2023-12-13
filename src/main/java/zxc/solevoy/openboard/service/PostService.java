package zxc.solevoy.openboard.service;

import zxc.solevoy.openboard.entity.Post;
import java.util.List;

public interface PostService {

    List<Post> getAllPosts();
    void createPost(Post post);
    void updatePost(Post post);
    void editPost(Post post);
    void deletePost(Long id);
    Post findPostById(Long id);
}

