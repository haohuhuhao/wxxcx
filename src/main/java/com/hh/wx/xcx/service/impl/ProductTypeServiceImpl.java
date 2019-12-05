package com.hh.wx.xcx.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hh.wx.xcx.commons.LoginInfoUtils;
import com.hh.wx.xcx.commons.LoginUserInfo;
import com.hh.wx.xcx.commons.ResultUtils;
import com.hh.wx.xcx.commons.ResultVo;
import com.hh.wx.xcx.model.AppForProduct;
import com.hh.wx.xcx.model.ProductType;
import com.hh.wx.xcx.service.ProductTypeService;
import com.hh.wx.xcx.service.mapper.ProductTypeMapper;

@Service
public class ProductTypeServiceImpl implements ProductTypeService {

	@Autowired
	private ProductTypeMapper productTypeMapper;

	@Override
	public ResultVo<String> add(ProductType productType) {
		productTypeMapper.insert(productType);
		return ResultUtils.secusses();
	}

	@Override
	public ResultVo<List<ProductType>> queryAll(ProductType productType) {
		List<ProductType> productTypes = productTypeMapper.queryAll(productType);
		return ResultUtils.secusses(productTypes);
	}

	@Override
	public ResultVo<ProductType> getById(Long id) {
		return ResultUtils.secusses(productTypeMapper.getById(id));
	}

	@Override
	public ResultVo<String> update(ProductType productType) {
		if(productType.getId() == null){
			return ResultUtils.fail("分类id不能为空");
		}
		
		productTypeMapper.update(productType);
		return ResultUtils.secusses();
	}

	@Override
	public ResultVo<String> deleteById(Long id) {
		productTypeMapper.deleteById(id);
		return ResultUtils.secusses();
	}

	@Override
	public ResultVo<List<ProductType>> getProductType(String businessType, Long appId) {
		List<ProductType> types = productTypeMapper.queryProductType(businessType,appId);
		return ResultUtils.secusses(types);
	}

	@Override
	public ResultVo<List<ProductType>> getProductType() {
		LoginUserInfo loginUserInfo = LoginInfoUtils.getLoginInfo(LoginUserInfo.class);
		return getProductType(loginUserInfo.getBusinessType(),loginUserInfo.getAppId());
	}

	@Override
	public ResultVo<String> addAppToType(AppForProduct appForProduct) {
		productTypeMapper.addMapping(appForProduct);
		return ResultUtils.secusses("操作成功");
	}
	
}
