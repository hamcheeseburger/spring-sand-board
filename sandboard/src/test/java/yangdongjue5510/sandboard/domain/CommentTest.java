package yangdongjue5510.sandboard.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class CommentTest {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Test
    void setPost() {
        final Post post = Post.builder()
                .title("title")
                .content("content")
                .comments(new ArrayList<>())
                .build();
        postRepository.save(post);

        final Comment comment1 = Comment.builder()
                .content("comment1")
                .build();

        final Comment comment2 = Comment.builder()
                .content("comment2")
                .build();

        comment1.setPost(post);
        comment2.setPost(post);

        commentRepository.save(comment1);
        commentRepository.save(comment2);

        assertThat(post.getComments()).hasSize(2);
    }

    @Test
    void findByIdField() {
        final Comment comment1 = Comment.builder()
                .content("comment1")
                .build();
        Post post = Post
                .builder()
                .title("title")
                .content("content")
                .comments(new ArrayList<>())
                .build();

        comment1.setPost(post);
        postRepository.save(post);
        commentRepository.save(comment1);

        final Post foundPost = postRepository.findById(post.getId()).get();

        assertThat(foundPost.getComments()).isNotNull();
    }
}
