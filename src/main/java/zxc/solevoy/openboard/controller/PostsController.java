package zxc.solevoy.openboard.controller;

import jakarta.transaction.Transactional;
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
    public String showMainPage() {
        return "redirect:/feed";
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

        return "redirect:/feed";
    }

    @GetMapping("/deletepost/{id}")
    public String deletePostForm(@PathVariable Long id, Model model){
        model.addAttribute("post", postService.findPostById(id));

        return "deletePost";
    }

    @GetMapping("/deletepost")
    public String emptyDeleteForm(){
        return "redirect:/feed";
    }

    @Transactional
    @PostMapping("/removepost/{id}")
    public String removePost(@PathVariable Long id){
        postService.deletePost(id);
        return "redirect:/feed";
    }

    @GetMapping("/post/{id}")
    public String findPost(@PathVariable Long id){
        return "redirect:/deletepost/" + id;
    }
}