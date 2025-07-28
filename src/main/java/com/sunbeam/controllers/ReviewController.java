package com.sunbeam.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sunbeam.entities.Product;
import com.sunbeam.entities.Review;
import com.sunbeam.entities.User;
import com.sunbeam.request.CreateReviewRequest;
import com.sunbeam.response.ApiResponse;
import com.sunbeam.services.ReviewService;
import com.sunbeam.services.UserService;
import com.sunbeam.services.UserService1;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReviewController {

	private final ReviewService reviewService;
	private final UserService1 userService;
//	private final ProductService productService;
	
	
	@GetMapping("/products/{productId}/reviews")
	public ResponseEntity<List<Review>> getReviewByProductId(
			@PathVariable Long productId){
		List<Review> reviews = reviewService.getReviewByProductId(productId);
		return ResponseEntity.ok(reviews);
	}
	
	
//	@PostMapping("/products/{productId}/reviews")
//	public ResponseEntity<Review> writeReview(
//			@RequestBody CreateReviewRequest req,
//			@PathVariable Long productId,
//			@RequestHeader("Authorization") String jwt) throws Exception{
//		
//		User user = userService.findUserProfileByJwt(jwt); 		
//		Product product = productService.findProductById(productId);
//
//		Review review = reviewService.createReview(req, user, product);
//		return ResponseEntity.ok(review);	
//	}
	
	
	@PatchMapping("/reviews/{reviewId}")
	public ResponseEntity<Review> updateReview(
			@RequestBody CreateReviewRequest req,
			@PathVariable Long reviewId,
			@RequestHeader("Authorization") String jwt) throws Exception{
		
		User user = userService.findUserProfileByJwt(jwt);
		
		Review review = reviewService.updateReview(
				reviewId, 
				req.getReviewText(), 
				req.getReviewRating(), 
				user.getId());
		return ResponseEntity.ok(review);
	}
	
	
	@DeleteMapping("/reviews/{reviewId}")
	public ResponseEntity<ApiResponse> deleteReview(
			@PathVariable Long reviewId,
			@RequestHeader("Authentication") String jwt) throws Exception {
		
		User user = userService.findUserProfileByJwt(jwt);
		
		reviewService.deleteReview(reviewId, user.getId());
		
		ApiResponse res = new ApiResponse();
		res.setMessage("Review Deleted Successfully");

		return ResponseEntity.ok(res);
	}
	
	
	
	
}
