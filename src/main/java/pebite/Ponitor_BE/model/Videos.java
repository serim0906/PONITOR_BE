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
@Table(name="videos")
public class Videos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="videoId")
    private Long videoId;

    @Column(name="customerId")
    private Long customerId;

    @Column(name="origFileName")
    private String origFileName;

    @Column(name="filePath")
    private String filePath;

    @Column(name="fileName")
    private String fileName;

}
