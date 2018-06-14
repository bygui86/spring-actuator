package com.rabbitshop.springactuatorsample.domain.controllers;

import com.rabbitshop.springactuatorsample.domain.data.MailDto;
import com.rabbitshop.springactuatorsample.domain.services.MailService;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@RestController
@RequestMapping(value = "/mail")
public class MailController {

	@Autowired
	MailService mailService;

	@PostMapping(path = "/send")
	@ResponseStatus(value = HttpStatus.OK)
	public void sendMail(@RequestBody final MailDto mailDto) {

		log.debug("Send email");

		getMailService().sendEmail(mailDto);
	}

}
