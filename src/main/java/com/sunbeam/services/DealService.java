package com.sunbeam.services;

import java.util.List;

import com.sunbeam.entities.Deal;

public interface DealService {
//  List<Deal> createDeals(List<Deal> deals);
  List<Deal> getDeals();
  Deal createDeal(Deal deal);
  Deal updateDeal(Deal deal,Long id) throws Exception;
  void deleteDeal(Long id) throws Exception;


}
