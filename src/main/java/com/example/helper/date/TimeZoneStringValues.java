package com.example.helper.date;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public enum TimeZoneStringValues {

	TIME_ZONE_MOSCOW(
			"Europe/Moscow"
	),
	TIME_ZONE_UTC(
			"UTC"
	),
	TIME_ZONE_UTC_MOSCOW(
			"UTC+03:00"
	);

	private final String value;
}