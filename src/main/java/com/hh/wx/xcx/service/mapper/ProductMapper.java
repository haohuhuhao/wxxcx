package com.hh.wx.xcx.service.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.hh.wx.xcx.model.Product;
import com.hh.wx.xcx.model.ProductType;

@Mapper
public interface ProductMapper {

	void insert(Product product);

	List<Product> queryAll(Product product);

	Product getById(Long id);

	void update(Product product);

	void deleteById(Long id);

	void changeStatus(@Param("id") Long id,@Param("status") Integer status);
}
