package yangdongjue5510.sandboard.dto;

import lombok.Getter;

@Getter
public class PostCreateRequest {

    private String title;
    private String content;

    public PostCreateRequest(final String title, final String content) {
        this.title = title;
        this.content = content;
    }

    private PostCreateRequest() {
    }
}
