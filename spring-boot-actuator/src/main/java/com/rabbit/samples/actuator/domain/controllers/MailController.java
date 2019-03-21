package com.rabbit.samples.actuator.domain.controllers;

import com.rabbit.samples.actuator.constants.Endpoints;
import com.rabbit.samples.actuator.domain.data.Mail;
import com.rabbit.samples.actuator.domain.services.MailService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter(AccessLevel.PROTECTED)
@RestController
@RequestMapping(Endpoints.MAIL)
public class MailController {

	MailService mailService;

	@PostMapping
	@ResponseStatus(value = HttpStatus.OK)
	public void sendMail(@RequestBody final Mail mail) {

		log.debug("Send email");

		getMailService().sendEmail(mail);
	}

}
