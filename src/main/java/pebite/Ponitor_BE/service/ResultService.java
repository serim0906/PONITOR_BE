package pebite.Ponitor_BE.service;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pebite.Ponitor_BE.dto.ResultSaveRequestDto;
import pebite.Ponitor_BE.repository.ResultRepository;

@RequiredArgsConstructor
@Service
public class ResultService {
    private final ResultRepository resultRepository;
    @Transactional
    public Long save(ResultSaveRequestDto requestDto){
        return resultRepository.save(requestDto.toEntity()).getCustomerId();
    }

}
