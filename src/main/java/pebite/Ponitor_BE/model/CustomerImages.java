package pebite.Ponitor_BE.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class CustomerImages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageId; //자동생성

    @Column(nullable = true)
    private Long customerId;

    // 원본 파일이름과 서버에 저장된 파일경로 를 분리한 이유?
    // 동일한 이름을 가진 파일이 업로드되면 오류발생
    @Column(nullable = true, length = 1000)
    private String originalImageName; //프론트에서 보내는 파일명

    @Column( length = 1000)
    private String storedImageName; //서버 내부에서 관리하는 파일명

    @Column(length = 1000)
    private String imageUrl;

    @Builder
    public CustomerImages(Long customerId, String originalImageName, String storedImageName,String imageUrl){
        this.customerId = customerId;
        this.originalImageName = originalImageName;
        this.storedImageName = storedImageName;
        this.imageUrl = imageUrl;
    }


}
