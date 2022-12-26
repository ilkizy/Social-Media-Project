package com.ilkiz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MailServiceApplicaton {

/**    @Autowired
    EmailSenderService senderService;
**/

    public static void main(String[] args) {
        SpringApplication.run(MailServiceApplicaton.class,args);
    }

/**    @EventListener(ApplicationReadyEvent.class)
    public void SendMail(){
        ActivatedRequestDto dto = ActivatedRequestDto.builder().email("bugracabuk@gmail.com").activatedCode("İlkiz Buğrayı seviyooor").build();
        senderService.sendActivateCode(dto);
    }
**/
}