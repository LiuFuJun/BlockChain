package com.blockchain.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonUtil {

	private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

	public static final Gson getGson() {
		if (null == gson) {
			gson = new GsonBuilder().setPrettyPrinting().create();
		}
		return gson;
	}

}
