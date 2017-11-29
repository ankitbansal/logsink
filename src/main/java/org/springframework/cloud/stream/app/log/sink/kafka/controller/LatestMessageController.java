package org.springframework.cloud.stream.app.log.sink.kafka.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by anbabans on 11/27/2017.
 */
@RestController
@RequestMapping("latest")
public class LatestMessageController {
    @RequestMapping("/console")
    public void latestMessages(HttpServletRequest request, HttpServletResponse response) throws Exception{
        ServletOutputStream out = response.getOutputStream();
        MaxSizeHashMap<String, String> instance = MaxSizeHashMap.instance;
        for(Map.Entry<String, String> entry: instance.entrySet()) {
            out.println(entry.getValue());
        }

        out.close();
    }
}
