package com.rabbitshop.springactuatorsample.domain.data;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;


@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class MailDto implements Serializable {

	String recipient;

	String subject;

	String text;

}
