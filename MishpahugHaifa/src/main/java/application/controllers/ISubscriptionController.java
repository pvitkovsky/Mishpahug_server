package application.controllers;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;

import application.models.user.values.FeedBackValue;

public interface ISubscriptionController {

	public void subscribe(HttpHeaders httpHeaders, HttpServletRequest request, Integer eventId, Integer userId);

	public void deactivateSubscription(HttpHeaders httpHeaders, HttpServletRequest request, Integer eventId,
			Integer userId);

	public Map<Integer, FeedBackValue> getFeedback(HttpHeaders httpHeaders, HttpServletRequest request, Integer userId,
			Integer eventId);

}