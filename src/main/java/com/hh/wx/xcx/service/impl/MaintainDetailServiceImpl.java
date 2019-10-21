package com.hh.wx.xcx.service.impl;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hh.wx.xcx.commons.ResultUtils;
import com.hh.wx.xcx.commons.ResultVo;
import com.hh.wx.xcx.model.MaintainDetail;
import com.hh.wx.xcx.service.MaintainDetailService;
import com.hh.wx.xcx.service.mapper.MaintainDetailMapper;

@Service
public class MaintainDetailServiceImpl implements MaintainDetailService {

	@Autowired
	private MaintainDetailMapper maintainDetailMapper;

	@Override
	public ResultVo<String> create(MaintainDetail maintainDetail) {
		
		maintainDetailMapper.insert(maintainDetail);
		return ResultUtils.secusses();
	}

	@Override
	public ResultVo<List<MaintainDetail>> queryBymaintainInfoId(Long maintainInfoId) {
		List<MaintainDetail> maintainDetails = maintainDetailMapper.queryBymaintainInfoId(maintainInfoId);
		return ResultUtils.secusses(maintainDetails);
	}

	@Override
	public ResultVo<String> update(MaintainDetail maintainDetail) {
		maintainDetailMapper.update(maintainDetail);
		return ResultUtils.secusses();
	}

	@Override
	public ResultVo<String> bacthCreate(List<MaintainDetail> maintainDetails) {
		
		maintainDetailMapper.bacthInsert(maintainDetails);
		return ResultUtils.secusses();
	}

	@Override
	public ResultVo<String> batchUpdate(List<MaintainDetail> maintainDetails) {
		maintainDetailMapper.batchUpdate(maintainDetails);
		return ResultUtils.secusses();
	}

}
