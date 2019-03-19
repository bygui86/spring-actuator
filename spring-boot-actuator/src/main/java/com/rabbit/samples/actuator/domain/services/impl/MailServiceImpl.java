package com.rabbit.samples.actuator.domain.services.impl;

import com.rabbit.samples.actuator.domain.data.Mail;
import com.rabbit.samples.actuator.domain.services.MailService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter(AccessLevel.PROTECTED)
@Service
public class MailServiceImpl implements MailService {

	static final String MAIL_SENDER = "sender@rabbitshop.com";

	JavaMailSender emailSender;

	@Override
	public void sendEmail(final Mail mail) {

		final String recipient = mail.getRecipient();
		final String subject = mail.getSubject();
		final String text = mail.getText();

		log.debug("Sending email to {} with subject {}: {}", recipient, subject, text);

		final SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(recipient);
		message.setSubject(subject);
		message.setText(text);
		message.setFrom(MAIL_SENDER);
		getEmailSender().send(message);
	}

}
