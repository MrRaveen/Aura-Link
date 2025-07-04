package com.example.notificationService.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notification")
@CrossOrigin
public class AccessPoint {
	//get the notifications
	@GetMapping("/getNotifications")
	public String getAllNotifications(@RequestParam int userID) {
		/*
		 * get the notifications from mongo db
		 * get the image link
		 * map the response 
		 * send the response
		 * */
		return "";
	}
	//update reading status by click
	@PostMapping("/updateNotStatusByClick")
	public String updateNotificationSatatusByClick(@RequestParam int userID,@RequestParam String notificationID) {
		return "";
	}
	//remove the notification
	@DeleteMapping("/removeNotification")
	public String deleteProcess(@RequestParam String notificationID) {
		return "";
	}
}
