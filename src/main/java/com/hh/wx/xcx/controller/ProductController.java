package com.hh.wx.xcx.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hh.wx.xcx.commons.ResultVo;
import com.hh.wx.xcx.model.Product;
import com.hh.wx.xcx.service.ProductService;

@RestController
@RequestMapping("product")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	

	@RequestMapping(value="add",method=RequestMethod.POST)
	public ResultVo<String> add(@RequestBody Product product){
		
		return productService.add(product);
	}
	
	@RequestMapping(value="query",method=RequestMethod.POST)
	public ResultVo<List<Product>> query(@RequestBody Product product){
		
		return productService.queryAll(product);
	}
	
	@RequestMapping(value="get/{id}",method=RequestMethod.GET)
	public ResultVo<Product> query(@PathVariable("id") Long id){
		
		return productService.getById(id);
	}
	
	@RequestMapping(value="update",method=RequestMethod.PUT)
	public ResultVo<String> update(@RequestBody Product product){
		
		return productService.update(product);
	}
	
	@RequestMapping(value="delete/{id}",method=RequestMethod.DELETE)
	public ResultVo<String> delete(@PathVariable("id") Long id){
		
		return productService.deleteById(id);
	}
	
	@RequestMapping(value="changeStatus/{id}/{status}",method=RequestMethod.PUT)
	public ResultVo<String> changeStatus(@PathVariable("id") Long id,@PathVariable("status") Integer status){
		
		return productService.changeStatus(id, status);
	}
}

