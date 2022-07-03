package yangdongjue5510.sandboard.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.*;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import yangdongjue5510.sandboard.domain.Post;
import yangdongjue5510.sandboard.domain.PostRepository;
import yangdongjue5510.sandboard.dto.PostCreateRequest;

@ExtendWith(MockitoExtension.class)
class PostServiceTest {

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private PostService postService;

    @Test
    void 포스트를_생성한다() {

        // given
        final Post post = Post.builder()
                .title("title")
                .content("content")
                .build();

        given(postRepository.save(any(Post.class)))
                .willReturn(writeId(post, 1L));

        // when
        Long id = postService.createPost(new PostCreateRequest("title", "content"));

        // then
        assertThat(id).isOne();
    }

    private Post writeId(final Post post, final Long id) {
        try {
            FieldUtils.writeField(post, "id", id, true);
            return post;
        } catch (IllegalAccessException e) {
            throw new RuntimeException("ID 리플렉션 실패");
        }
    }
}
