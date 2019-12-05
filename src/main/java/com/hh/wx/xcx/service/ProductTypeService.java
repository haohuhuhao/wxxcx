package com.hh.wx.xcx.service;

import java.util.List;

import com.hh.wx.xcx.commons.ResultVo;
import com.hh.wx.xcx.model.AppForProduct;
import com.hh.wx.xcx.model.ProductType;

public interface ProductTypeService {

	ResultVo<String> add(ProductType productType);

	ResultVo<List<ProductType>> queryAll(ProductType productType);

	ResultVo<ProductType> getById(Long id);

	ResultVo<String> update(ProductType productType);
	
	ResultVo<String> deleteById(Long id);

	ResultVo<List<ProductType>> getProductType(String businessType,Long appId);

	ResultVo<List<ProductType>> getProductType();

	ResultVo<String> addAppToType(AppForProduct appForProduct);

}
