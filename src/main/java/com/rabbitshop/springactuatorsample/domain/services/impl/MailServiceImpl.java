package com.rabbitshop.springactuatorsample.domain.services.impl;

import com.rabbitshop.springactuatorsample.domain.data.MailDto;
import com.rabbitshop.springactuatorsample.domain.services.MailService;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Service("mailService")
public class MailServiceImpl implements MailService {

	static final String MAIL_SENDER = "sender@rabbitshop.com";

	@Autowired
	JavaMailSender emailSender;

	@Override
	public void sendEmail(final MailDto mailDto) {

		final String recipient = mailDto.getRecipient();
		final String subject = mailDto.getSubject();
		final String text = mailDto.getText();

		log.debug("Sending email to {} with subject {}: {}", recipient, subject, text);

		final SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(recipient);
		message.setSubject(subject);
		message.setText(text);
		message.setFrom(MAIL_SENDER);
		getEmailSender().send(message);
	}

}
