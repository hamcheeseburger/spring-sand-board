package yangdongjue5510.sandboard.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;

@Entity
@Table(name = "comment")
@Getter
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @ManyToOne
    private Post post;

    protected Comment() {

    }

    @Builder
    public Comment(final Long id, final String content, final Post post) {
        this.id = id;
        this.content = content;
        this.post = post;
    }

    public void setPost(final Post post) {
        this.post = post;
        post.getComments().add(this);
    }
}
