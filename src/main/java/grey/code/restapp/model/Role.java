package grey.code.restapp.model;

/*
grey.code.restapp.model
Tarih: 23.06.2022, Saat: 12:13, Author: Grey 
*/

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Role")
@Setter
@Getter
@NoArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String roleName;

    @ManyToMany(targetEntity = User.class, mappedBy = "roles",cascade = CascadeType.ALL)
    List<User> users;

}
