package com.hh.wx.xcx.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hh.wx.xcx.commons.ResultVo;
import com.hh.wx.xcx.model.AppForProduct;
import com.hh.wx.xcx.model.ProductType;
import com.hh.wx.xcx.service.ProductTypeService;

@RestController
@RequestMapping("types")
public class ProductTypeController {
	
	@Autowired
	private ProductTypeService productTypeService;
	

	@RequestMapping(value="add",method=RequestMethod.POST)
	public ResultVo<String> add(@RequestBody ProductType productType){
		
		return productTypeService.add(productType);
	}
	
	@RequestMapping(value="query",method=RequestMethod.POST)
	public ResultVo<List<ProductType>> query(@RequestBody ProductType productType){
		
		return productTypeService.queryAll(productType);
	}
	
	@RequestMapping(value="get/{id}",method=RequestMethod.GET)
	public ResultVo<ProductType> query(@PathVariable("id") Long id){
		
		return productTypeService.getById(id);
	}
	
	@RequestMapping(value="update",method=RequestMethod.PUT)
	public ResultVo<String> update(@RequestBody ProductType productType){
		
		return productTypeService.update(productType);
	}
	
	@RequestMapping(value="delete/{id}",method=RequestMethod.DELETE)
	public ResultVo<String> delete(@PathVariable("id") Long id){
		
		return productTypeService.deleteById(id);
	}
	
	@RequestMapping(value="types",method=RequestMethod.GET)
	public ResultVo<List<ProductType>> getProductType(String businessType,Long appId){
		
		return productTypeService.getProductType(businessType,appId);
	}
	
	@RequestMapping(value="types/withLogin",method=RequestMethod.GET)
	public ResultVo<List<ProductType>> getProductType(){
		
		return productTypeService.getProductType();
	}
	
	@RequestMapping(value="types/addAppTo",method=RequestMethod.POST)
	public ResultVo<String> addAppToType(@RequestBody AppForProduct appForProduct){
		
		return productTypeService.addAppToType(appForProduct);
	}
}

