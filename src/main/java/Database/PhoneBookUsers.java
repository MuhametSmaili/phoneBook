package Database;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class PhoneBookUsers extends AbstractEntity{

    @Column
    private String Name;
    @Column
    private String Surname;
    @Column
    private Long Number;

    public PhoneBookUsers(String name, String surname, Long number) {
        Name = name;
        Surname = surname;
        Number = number;
    }
}
