package yangdongjue5510.sandboard.presentation;

import java.net.URI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yangdongjue5510.sandboard.application.PostService;
import yangdongjue5510.sandboard.dto.PostCreateRequest;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    public PostController(final PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<Void> createPost(@RequestBody PostCreateRequest postCreateRequest) {
        final Long postedId = postService.createPost(postCreateRequest);
        return ResponseEntity.created(URI.create("/api/posts/" + postedId)).build();
    }
}
