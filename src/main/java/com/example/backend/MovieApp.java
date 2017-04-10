package com.example.backend;

import java.util.Map;

import com.example.util.Util;

public class MovieApp {

	public static void main(String args[]) {
		
		final MovieService service = new MovieService(Util.getNeo4jUrl());
		Map map = service.findMovie("Top Gun");
		
		System.out.println(map);
	}
	
}
