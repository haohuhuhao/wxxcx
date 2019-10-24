package com.hh.wx.xcx.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hh.wx.xcx.commons.LoginInfoUtils;
import com.hh.wx.xcx.commons.LoginUserInfo;
import com.hh.wx.xcx.commons.ResultUtils;
import com.hh.wx.xcx.commons.ResultVo;
import com.hh.wx.xcx.model.Product;
import com.hh.wx.xcx.service.ProductService;
import com.hh.wx.xcx.service.mapper.ProductMapper;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductMapper productMapper;

	@Override
	public ResultVo<String> add(Product product) {
		productMapper.insert(product);
		return ResultUtils.secusses();
	}

	@Override
	public ResultVo<List<Product>> queryAll(Product product) {
		LoginUserInfo loginUserInfo = LoginInfoUtils.getLoginInfo(LoginUserInfo.class);
		product.setAppId(loginUserInfo.getAppId());
		List<Product> products = productMapper.queryAll(product);
		return ResultUtils.secusses(products);
	}

	@Override
	public ResultVo<Product> getById(Long id) {
		return ResultUtils.secusses(productMapper.getById(id));
	}

	@Override
	public ResultVo<String> update(Product product) {
		if(product.getId() == null){
			return ResultUtils.fail("应用id不能为空");
		}
		
		productMapper.update(product);
		return ResultUtils.secusses();
	}

	@Override
	public ResultVo<String> deleteById(Long id) {
		productMapper.deleteById(id);
		return ResultUtils.secusses();
	}

	@Override
	public ResultVo<String> changeStatus(Long id, Integer status) {
		productMapper.changeStatus(id,status);
		return ResultUtils.secusses();
	}
	
}
