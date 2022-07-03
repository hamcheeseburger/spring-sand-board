package yangdongjue5510.sandboard.presentation;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import yangdongjue5510.sandboard.application.PostService;
import yangdongjue5510.sandboard.dto.PostCreateRequest;

@WebMvcTest(PostController.class)
class PostControllerTest {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostService postService;

    @Test
    void 제목과_내용을_받아_게시물을_생성한다() throws Exception {
        // given
        final String bodyString = OBJECT_MAPPER.writeValueAsString(new PostCreateRequest("title", "content"));

        given(postService.createPost(any(PostCreateRequest.class)))
                .willReturn(1L);

        // when
        final ResultActions resultActions = mockMvc.perform(
                post("/api/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bodyString)
                        .accept(MediaType.APPLICATION_JSON)
        );

        //  then
        resultActions
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/api/posts/1"));
    }
}
