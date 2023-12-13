package zxc.solevoy.openboard.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import zxc.solevoy.openboard.entity.Post;
import zxc.solevoy.openboard.service.PostService;

import java.util.List;

@Controller
@AllArgsConstructor
public class PostsController {

    private final PostService postService;

    @GetMapping
    public String showMainPage(Model model) {
        return "feed";
    }

    @GetMapping("/feed")
    public String showFeed(Model model) {
        List<Post> postList = postService.getAllPosts();
        model.addAttribute("posts", postList);

        return "feed";
    }



    @GetMapping("/createpost")
    public String getAddPostForm(Model model) {
        model.addAttribute("post", new Post());

        return "addPost";
    }

    @PostMapping("/addpost")
    public String addPost(Post post, Model model) {
        postService.createPost(post);

        return "redirect:/createpost";
    }

    @ResponseBody
    @GetMapping("/post/{id}")
    public Post findPost(@PathVariable Long id){
        return postService.findPostById(id);
    }
}