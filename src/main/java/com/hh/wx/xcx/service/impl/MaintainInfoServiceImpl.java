package com.hh.wx.xcx.service.impl;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hh.wx.xcx.commons.LoginInfoUtils;
import com.hh.wx.xcx.commons.LoginUserInfo;
import com.hh.wx.xcx.commons.ResultUtils;
import com.hh.wx.xcx.commons.ResultVo;
import com.hh.wx.xcx.model.MaintainInfo;
import com.hh.wx.xcx.service.MaintainInfoService;
import com.hh.wx.xcx.service.mapper.MaintainInfoMapper;

@Service
public class MaintainInfoServiceImpl implements MaintainInfoService {

	@Autowired
	private MaintainInfoMapper maintainInfoMapper;

	@Override
	public ResultVo<String> create(MaintainInfo maintainInfo) {
		LoginUserInfo user = LoginInfoUtils.getLoginInfo(LoginUserInfo.class);
		maintainInfo.setAppId(user.getAppId());
		
		maintainInfoMapper.insert(maintainInfo);
		return ResultUtils.secusses();
	}

	@Override
	public ResultVo<List<MaintainInfo>> queryByCarId(Long carId,Integer status) {
		List<MaintainInfo> maintainInfos = maintainInfoMapper.queryByCarIdAndAppId(carId,
				LoginInfoUtils.getLoginInfo(LoginUserInfo.class).getAppId(),
				status);
		return ResultUtils.secusses(maintainInfos);
	}

	@Override
	public ResultVo<String> update(MaintainInfo maintainInfo) {
		maintainInfoMapper.update(maintainInfo);
		return ResultUtils.secusses();
	}

}
