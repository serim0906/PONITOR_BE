package pebite.Ponitor_BE.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Table(name="users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="userId")
    private Long userId;

    @Column(name="username")
    private String username;

    @Column(name="password")
    private String password;

    @Column(name="authority")
    private String authority;

    @Column(name="atmBranch")
    private String atmBranch;

    @Column(name="atmId")
    private String atmId;

}
