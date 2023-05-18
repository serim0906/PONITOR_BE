package pebite.Ponitor_BE.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import lombok.Setter;
import pebite.Ponitor_BE.model.CustomerImages;

@Setter
@Getter
@NoArgsConstructor
public class CustomerImagesSaveRequestDto {

    private Long customerId;
    private String originalImageName;
    private String storedImageName;
    private String imageUrl;

    @Builder
    public CustomerImagesSaveRequestDto(Long customerId, String originalImageName, String storedImageName, String imageUrl){
        this.customerId = customerId;
        this.originalImageName = originalImageName;
        this.storedImageName = storedImageName;
        this.imageUrl = imageUrl;

    }

    public CustomerImages toEntity(){
        return CustomerImages.builder()
                .customerId(customerId)
                .originalImageName(originalImageName)
                .storedImageName(storedImageName)
                .imageUrl(imageUrl)
                .build();
    }
}
