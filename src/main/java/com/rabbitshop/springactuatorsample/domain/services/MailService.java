package com.rabbitshop.springactuatorsample.domain.services;

import com.rabbitshop.springactuatorsample.domain.data.MailDto;


public interface MailService {

	void sendEmail(final MailDto mailDto);

}
