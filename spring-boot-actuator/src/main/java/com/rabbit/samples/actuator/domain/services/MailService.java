package com.rabbit.samples.actuator.domain.services;

import com.rabbit.samples.actuator.domain.data.Mail;


public interface MailService {

	void sendEmail(final Mail mail);

}
