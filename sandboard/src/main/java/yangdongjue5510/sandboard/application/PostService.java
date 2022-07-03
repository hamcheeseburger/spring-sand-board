package yangdongjue5510.sandboard.application;

import org.springframework.stereotype.Service;
import yangdongjue5510.sandboard.domain.Post;
import yangdongjue5510.sandboard.domain.PostRepository;
import yangdongjue5510.sandboard.dto.PostCreateRequest;

@Service
public class PostService {


    private final PostRepository postRepository;

    public PostService(final PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Long createPost(final PostCreateRequest postCreateRequest) {
        final Post post = Post.builder()
                .title(postCreateRequest.getTitle())
                .content(postCreateRequest.getContent())
                .build();
        return postRepository.save(post).getId();
    }
}
