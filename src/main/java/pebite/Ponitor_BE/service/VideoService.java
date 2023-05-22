package pebite.Ponitor_BE.service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;
import pebite.Ponitor_BE.dto.EmoticDto;
import pebite.Ponitor_BE.dto.VideosResDto;
import pebite.Ponitor_BE.exception.ResourceNotFoundException;
import pebite.Ponitor_BE.model.*;
import pebite.Ponitor_BE.repository.customer.CustomerRepository;
import pebite.Ponitor_BE.repository.result.ResultRepository;
import pebite.Ponitor_BE.repository.users.UsersRepository;
import pebite.Ponitor_BE.repository.videos.VideosRepository;
import pebite.Ponitor_BE.util.SHA256;
import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;


@Slf4j
@Service
@Transactional
public class VideoService {

    @Autowired
    VideosRepository videosRepository;

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ResultRepository resultRepository;


    private AmazonS3 s3Client;
    private AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    @Value("${cloud.aws.credentials.accessKey}")
    private String accessKey;

    @Value("${cloud.aws.credentials.secretKey}")
    private String secretKey;

    @Value("${cloud.aws.region.static}")
    private String region;

    private final String OS = System.getProperty("os.name");


    public List<VideosResDto> getAllVideos(String username) {

        List<VideosResDto> videosResDtos = new LinkedList<>();

        /*
        // username으로 해당 유저의 atmBranch 조회
        Users users = usersRepository.findByUsername(username).orElseThrow(ResourceNotFoundException::new);
        String atmBranch = users.getAtmBranch();

        // atmBranch로 해당 atmId 목록 조회
        List<Atm> atms = atmRepository.findByAtmBranch(atmBranch);

        //atmId 목록으로 해당 customer 목록 조회
       for(Atm atm : atms) {
           String atmId = atm.getAtmId();
           List<Customer> customers = customerRepository.findByAtmId(atmId);

           //customerId로 해당 videos 목록과 result 목록 조회
           for (Customer customer : customers){

               Long customerId = customer.getCustomerId();
               Optional<Result> optionalResult = resultRepository.findByCustomerId(customerId);

               Result result = new Result();

               if(optionalResult.isPresent()){
                   result = optionalResult.get();
               }

               List<Videos> videos = videosRepository.findByCustomerId(customerId);

               for(Videos video : videos){

                   VideosResDto videosResDto = VideosResDto.builder()
                           .customerId(customerId)
                           .startTime(customer.getStartTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                           .endTime(customer.getEndTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                           .atmId(atmId)
                           .victim(customer.isVictim())  // boolean
                           .filePath(video.getFilePath())
                           .withdraw(result.getWithdraw())
                           .phone(result.isPhone())  // boolean
                           .anger(result.getAnger())
                           .annoyance(result.getAnnoyance())
                           .disapproval(result.getDisapproval())
                           .disquietment(result.getDisquietment())
                           .doubtConfusion(result.getDoubtConfusion())
                           .sadness(result.getSadness())
                           .suffering(result.getSuffering())
                           .total(result.getTotal())
                           .build();

                   videosResDtos.add(videosResDto);
               }
           }
           return videosResDtos;
       }
        return videosResDtos;
    */

        //username으로 해당 유저의 atmId를 조회
        Users users = usersRepository.findByUsername(username).orElseThrow(ResourceNotFoundException::new);
        String atmId = users.getAtmId();

        //atmId로 해당 customer 목록 조회
        List<Customer> customers = customerRepository.findByAtmId(atmId);

        //customerId로 해당 videos 목록과 result 목록 조회
        for (Customer customer : customers) {

            Long customerId = customer.getCustomerId();
            Optional<Result> optionalResult = resultRepository.findByCustomerId(customerId);

            Result result = new Result();

            if (optionalResult.isPresent()) {
                result = optionalResult.get();
            }

            List<Videos> videos = videosRepository.findByCustomerId(customerId);

            for (Videos video : videos) {

                EmoticDto emotions = new EmoticDto(
                        result.getAnger(),
                        result.getAnnoyance(),
                        result.getDisapproval(),
                        result.getDisquietment(),
                        result.getConfusion(),
                        result.getSadness(),
                        result.getSuffering(),
                        result.getTotal()
                );

                VideosResDto videosResDto = VideosResDto.builder()
                        .customerId(customerId)
                        .startTime(customer.getStartTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                        .endTime(customer.getEndTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                        .atmId(atmId)
                        .victim(customer.isVictim())  // boolean
                        .filePath(video.getFilePath())
                        .withdraw(result.getWithdraw())
                        .phone(result.isPhone())  // boolean
                        .emotions(emotions)
                        .build();

                videosResDtos.add(videosResDto);
            }
        }

        return videosResDtos;
    }


    public void uploadVideosAmazonS3(Long customerId, MultipartFile file)
            throws ResourceNotFoundException, MultipartException, IOException, NoSuchAlgorithmException {

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        String encryptedFileName = "";
        SHA256 sha256 = SHA256.getInstance();
        try {
            encryptedFileName = sha256.encrypt(fileName);
        }catch (NoSuchAlgorithmException e){
            throw new NoSuchAlgorithmException();
        }
        String fileExt = fileName.substring(fileName.lastIndexOf("."));
        encryptedFileName = encryptedFileName + fileExt;

        AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);

        s3Client = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(this.region)
                .build();

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(file.getContentType());

        try (InputStream inputStream = file.getInputStream()) {

            s3Client.putObject(new PutObjectRequest(bucketName, encryptedFileName, inputStream, objectMetadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead));

        } catch (IOException e) {
            throw new IOException();

        } catch (MultipartException e){
            throw new MultipartException(e.getMessage());
        }

        String filePath = s3Client.getUrl(bucketName, encryptedFileName).toString();

        Videos videos = Videos.builder()
                .customerId(customerId)
                .origFileName(fileName)
                .filePath(filePath)
                .fileName(encryptedFileName)
                .build();

        videosRepository.save(videos);

        return;
    }

}
