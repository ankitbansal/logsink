/*
 * Copyright 2015-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.cloud.stream.app.log.sink.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.app.log.sink.kafka.controller.MaxSizeHashMap;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.util.Map;


@SpringBootApplication
@Import(LogSinkConfiguration.class)
public class LogSinkKafkaApplication {

	@RequestMapping("/console")
	public void latestMessages(HttpServletRequest request, HttpServletResponse response) throws Exception{
		ServletOutputStream out = response.getOutputStream();
		MaxSizeHashMap<String, String> instance = MaxSizeHashMap.instance;
		for(Map.Entry<String, String> entry: instance.entrySet()) {
			out.println(entry.getValue());
		}

		out.close();
	}
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(LogSinkKafkaApplication.class, args);
	}
}
