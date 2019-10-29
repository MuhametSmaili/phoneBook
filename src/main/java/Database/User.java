package Database;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class User  extends AbstractEntity{
    @Column
    private String Username;
    @Column
    private String Password;

    public User(String username, String password) {
        Username = username;
        Password = password;
    }
}