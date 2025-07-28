package com.sunbeam.services.impl;

import org.springframework.stereotype.Service;

import com.sunbeam.daos.SellerReportRepository;
import com.sunbeam.daos.SellerRepository;
import com.sunbeam.entities.Seller;
import com.sunbeam.entities.SellerReport;
import com.sunbeam.services.SellerReportService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SellerReportServiceImpl implements SellerReportService{

	private final SellerReportRepository sellerReportRepository;
	
	@Override
	public SellerReport getSellerReport(Seller seller) {
		
		SellerReport sr = sellerReportRepository.findBySellerId(seller.getId());
		
		if(sr == null) {
			SellerReport newReport = new SellerReport();
			newReport.setSeller(seller);
			return sellerReportRepository.save(newReport);
		}
		return sr;
	}

	
	
	@Override
	public SellerReport updateSellerReport(SellerReport sellerReport) {
		return sellerReportRepository.save(sellerReport);
	}

}
