package pebite.Ponitor_BE.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Table(name="atm")
public class Atm {

    @Id
    @Column(name="atmId")
    private String atmId;

    @Column(name="atmBranch")
    private String atmBranch;




}
