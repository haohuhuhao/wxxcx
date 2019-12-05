package com.hh.wx.xcx.service.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.hh.wx.xcx.model.AppForProduct;
import com.hh.wx.xcx.model.ProductType;

@Mapper
public interface ProductTypeMapper {

	void insert(ProductType productType);

	List<ProductType> queryAll(ProductType productType);

	ProductType getById(Long id);

	void update(ProductType productType);

	void deleteById(Long id);

	List<ProductType> queryProductType(@Param("businessType")String businessType, @Param("appId")Long appId);

	void addMapping(AppForProduct appForProduct);
}
