package com.sf.common.web.controller;

import com.sf.common.web.sse.SseClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ky
 * @description Server Send Event
 * @date 2024-04-19 21:42
 */
@Api(tags = "SSE")
@RestController
@RequestMapping("/sse")
public class SseController {

    @Autowired
    private SseClient sseClient;

    @ApiOperation("SSE 订阅")
    @GetMapping("/subscribe")
    public SseEmitter subscribe(String userId) {
        return sseClient.createSseEmitter(userId);
    }

    @ApiOperation("SSE 发布消息")
    @GetMapping("/push")
    public void push(String userId, String message) {
        sseClient.sendMessage(userId, message);
    }

    @ApiOperation("SSE 断开连接")
    @GetMapping(path = "disconnect")
    public void disconnect(String userId, HttpServletRequest request) {
        request.startAsync();
        sseClient.removeUser(userId);
    }
}
