package com.example.demoigl3springcloudstreamskafka.Web;
import  com.example.demoigl3springcloudstreamskafka.entities.PageEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.Date;
import java.util.Random;
@RestController
public class PageEventRestController {


        @Autowired
        private StreamBridge streamBridge;
        @GetMapping("/publish/{topic}/{name}")
        public PageEvent publish (@PathVariable String topic, @PathVariable String name) {
            PageEvent pageEvent=new PageEvent(name, Math.random()>0.5?"U1":"U2", new Date(), new Random().nextInt(900));
            streamBridge.send(topic,pageEvent);
            return pageEvent;
        }
}
