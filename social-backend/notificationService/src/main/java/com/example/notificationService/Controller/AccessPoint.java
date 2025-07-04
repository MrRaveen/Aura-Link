package com.example.notificationService.Controller;

import java.util.List;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.notificationService.Response.NotificationResponse;
import com.example.notificationService.Service.NotificationsService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notification")
@CrossOrigin
public class AccessPoint {
	@Autowired
	private NotificationsService service1;
	//get the notifications
	@GetMapping("/getNotifications")
	public ResponseEntity<List<NotificationResponse>> getAllNotifications(@RequestParam int userID) {
		/*
		 * get the notifications from mongo db
		 * get the image link
		 * map the response 
		 * send the response
		 * */
		try {
			List<NotificationResponse> out = service1.getNotifications(userID);
			return new ResponseEntity<List<NotificationResponse>>(out,null, HttpStatus.SC_OK);
		}catch(Exception e) {
			return new ResponseEntity<List<NotificationResponse>>(null,null, HttpStatus.SC_CONFLICT);
		}
	}
	//update reading status by click
	@PostMapping("/updateNotStatusByClick")
	public Boolean updateNotificationSatatusByClick(@RequestParam String notificationID) throws Exception {
		try {
			return service1.updateReadStatus(notificationID);
		}catch(Exception e) {
			throw new Exception("Error occued when updating status (AccessPoint.java) : " + e.toString());
		}
	}
	//remove the notification
	@DeleteMapping("/removeNotification")
	public ResponseEntity<Boolean> deleteProcess(@RequestParam String notificationID) {
		try {
			return new ResponseEntity<Boolean>(service1.removeProcessByID(notificationID),null,HttpStatus.SC_OK);
		}catch(Exception e) {
			return new ResponseEntity<Boolean>(false,null,HttpStatus.SC_CONFLICT);
		}
	}
}
