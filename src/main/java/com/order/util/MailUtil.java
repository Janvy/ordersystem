package com.order.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

/**
 * @Author: liuweijie
 * @CreateDate: 2021/2/26 14:52
 * @Version: 1.0
 * @Description:
 */
@Component
@Slf4j
public class MailUtil {

    @Value("${spring.mail.from:}")
    private String from;

    @Autowired
    private JavaMailSender javaMailSender;

    public void send(String title,String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(from);
        message.setSubject(title);
        message.setText(content);

        javaMailSender.send(message);
    }
}
