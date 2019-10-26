package Database;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@Getter
@Setter
@ToString
public abstract class AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @Temporal(TemporalType.DATE)
    public Date creationTime = new Date();
}
