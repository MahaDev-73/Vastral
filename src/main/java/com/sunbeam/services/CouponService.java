package com.sunbeam.services;



import java.util.List;
import java.util.Optional;

import com.sunbeam.entities.Cart;
import com.sunbeam.entities.Coupon;
import com.sunbeam.entities.User;

public interface CouponService {
	Cart applyCoupon(String code, double orderValue, User user) throws Exception;
    Cart removeCoupon(String code, User user) throws Exception;
    Coupon findCouponById(Long id) throws Exception;
    Coupon createCoupon(Coupon coupon);
    void deleteCoupon(Long couponId) throws Exception;
	List<Coupon> findAllCoupons();
     
}
