package com.example.helper.date;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public enum DateTimePattern {

	DATE_TIME_PATTERN(
			"yyyy-MM-dd HH:mm:ss"
	),
	DATE_TIME_LONG_PATTERN(
			"yyyy-MM-dd HH:mm:ss.SSS"
	),
	DATE_TIME_FOR_JIRA_RUNNER_PATTERN(
			"dd.MM.YYYY HH:mm:ss"
	),
	DATE_TIME_FOR_GENERATOR_PATTERN(
			"yyyyMMddHHmmssSSS"
	),
	DATE_TIME_FOR_GENERATOR_SHORT_PATTERN(
			"MddHH"
	),
	DATE_TIME_FOR_SQL_SHORT_PATTERN(
			"dd/MM/yyyy"
	),
	DATE_TIME_FOR_SITE_PATTERN(
			"dd/MM/yyyy HH:mm:ss"
	),
	DATE_TIME_UTC_PATTERN(
			"yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
	),
	DATE_TIME_UTC_MEDIUM_PATTERN(
			"yyyy-MM-dd'T'HH:mm:ss.SSS"
	),
	DATE_TIME_UTC_SHORT_PATTERN(
			"yyyy-MM-dd'T'HH:mm'Z'"
	),
	DATE_TIME_FOR_REST_WITH_TIMEZONE_PATTERN(
			"yyyy-MM-dd'T'HH:mm:ssX:00"
	),
	DATE_TIME_FOR_REST_WITH_TIMEZONE_WITHOUT_TIME_PATTERN(
			"yyyy-MM-dd'T'00:00:00X:00"
	);


	private final String value;
}
