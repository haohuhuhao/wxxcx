package com.hh.wx.xcx.service;

import java.util.List;

import com.hh.wx.xcx.commons.ResultVo;
import com.hh.wx.xcx.model.Product;

public interface ProductService {

	ResultVo<String> add(Product product);

	ResultVo<List<Product>> queryAll(Product product);

	ResultVo<Product> getById(Long id);

	ResultVo<String> update(Product product);
	
	ResultVo<String> changeStatus(Long id,Integer status);

	ResultVo<String> deleteById(Long id);

}
