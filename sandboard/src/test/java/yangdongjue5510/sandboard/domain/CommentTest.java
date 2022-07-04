package yangdongjue5510.sandboard.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class CommentTest {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @PersistenceContext
    private EntityManager entityManager;

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

    @Test
    void lazyLoadingWithProxy() {
        Post post = Post
                .builder()
                .title("title")
                .content("content")
                .comments(new ArrayList<>())
                .build();
        Comment comment1 = Comment.builder()
                .content("hi")
                .post(post)
                .build();
        Comment comment2 = Comment.builder()
                .content("hi")
                .post(post)
                .build();
        postRepository.save(post);
        commentRepository.save(comment1);
        commentRepository.save(comment2);
        entityManager.flush();
        entityManager.clear();
        final Comment foundComment = commentRepository.findById(2L)
                .orElseThrow();
        assertThat(comment1).isNotEqualTo(foundComment);
    }

    @Test
    void saveTwice() {
        Post post = Post
                .builder()
                .title("title")
                .content("content")
                .comments(new ArrayList<>())
                .build();
        final Post postHasId = Post.builder()
                .id(1L)
                .comments(new ArrayList<>())
                .content("content")
                .title("title")
                .build();
        postRepository.save(post);
        entityManager.clear();
        postRepository.save(postHasId);
        entityManager.flush();
        final List<Post> all = postRepository.findAll();
        assertThat(all).hasSize(2);
    }
}
