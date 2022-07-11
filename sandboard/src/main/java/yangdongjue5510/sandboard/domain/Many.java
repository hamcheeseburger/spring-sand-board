package yangdongjue5510.sandboard.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class Many {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
