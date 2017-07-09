package com.gb;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
// @ComponentScan(basePackages="com.gb")
@ComponentScan
public class GbApplication {

	public static void main(String[] args) {
		 SpringApplication.run(GbApplication.class, args);
	}
}
