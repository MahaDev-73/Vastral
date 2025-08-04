package com.sunbeam.services.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.sunbeam.daos.CartRepository;
import com.sunbeam.daos.CouponRepository;
import com.sunbeam.daos.UserRepository;
import com.sunbeam.entities.Cart;
import com.sunbeam.entities.Coupon;
import com.sunbeam.entities.User;
import com.sunbeam.exceptions.CouponNotValidException;
import com.sunbeam.services.CouponService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CouponServiceImpl implements CouponService {

    private final CouponRepository couponRepository;
    private final UserRepository userRepository;
    private final CartRepository cartRepository;

    @Override
    public Cart applyCoupon(String code, double orderValue, User user) throws Exception {
    	
        Coupon coupon = couponRepository.findByCode(code);
        Cart cart = cartRepository.findByUserId(user.getId());

        if (coupon==null) {
            throw new CouponNotValidException("coupon not found");
        }
        if(user.getUsedCoupons().contains(coupon)){
            throw new CouponNotValidException("coupon already used");
        }
        if(orderValue <= coupon.getMinimumOrderValue()){
            throw new CouponNotValidException("valid for minimum order value "+coupon.getMinimumOrderValue() );
        }

            if (
                    coupon.isActive() &&
                    LocalDate.now().isAfter(coupon.getValidityStartDate()) &&
                    LocalDate.now().isBefore(coupon.getValidityEndDate())


            ) {

                user.getUsedCoupons().add(coupon);
                userRepository.save(user);

                double discountedPrice = Math.round((cart.getTotalSellingPrice() * coupon.getDiscountPercentage()) / 100);
                cart.setTotalSellingPrice(cart.getTotalSellingPrice() - discountedPrice);
                cart.setCouponCode(code);
//                cart.setCouponPrice((int) discountedPrice);
                cartRepository.save(cart);
                return cart;
            }
            throw new CouponNotValidException("coupon not valid...");

    }

    
    @Override
    public Cart removeCoupon(String code, User user) throws Exception {
        Coupon coupon = couponRepository.findByCode(code);

        if(coupon==null){
            throw new Exception("coupon not found...");
        }
        user.getUsedCoupons().remove(coupon);

        Cart cart = cartRepository.findByUserId(user.getId());
        double discountedPrice = (cart.getTotalSellingPrice() * coupon.getDiscountPercentage()) / 100;
        cart.setTotalSellingPrice(cart.getTotalSellingPrice() + discountedPrice);
       
//        cart.setTotalSellingPrice(cart.getTotalSellingPrice()+cart.getCouponPrice());
        cart.setCouponCode(null);
//        cart.setCouponPrice(0);
        return cartRepository.save(cart);
    }
    
    
    @Override
	public Coupon findCouponById(Long id) throws Exception {
		return couponRepository.findById(id).orElseThrow(() -> 
								new Exception("Coupon not pound"));
	}

    
    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public Coupon createCoupon(Coupon coupon) {
        return couponRepository.save(coupon);
    }

    
    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteCoupon(Long couponId) throws Exception {
    	findCouponById(couponId);
        couponRepository.deleteById(couponId);
    }

    
    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public List<Coupon> findAllCoupons() {
        return couponRepository.findAll();
    }	
}
