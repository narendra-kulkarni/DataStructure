package com.mission.test.rand;

import java.util.UUID;

public class UUIDGenerator {

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++)
			System.out.println(UUID.randomUUID().toString());
	}
}
