package com.xcelerateit.service.Impl;

import com.xcelerateit.service.api.ResumeMatcherService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ResumeMatcherServiceImpl implements ResumeMatcherService {

    @Override
    public String sendResumeToPythonAPI(MultipartFile resumeFile) throws IOException {
        String pythonApiUrl = "http://localhost:5000/match-resume";

        RestTemplate restTemplate = new RestTemplate();

        ByteArrayResource fileAsResource = new ByteArrayResource(resumeFile.getBytes()) {
            @Override
            public String getFilename() {
                return resumeFile.getOriginalFilename();
            }
        };

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("resume_file", fileAsResource);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(pythonApiUrl, requestEntity, String.class);

        return response.getBody();

    }
}


