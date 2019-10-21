package com.hh.wx.xcx.service.impl;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hh.wx.xcx.commons.LoginInfoUtils;
import com.hh.wx.xcx.commons.LoginUserInfo;
import com.hh.wx.xcx.commons.ResultUtils;
import com.hh.wx.xcx.commons.ResultVo;
import com.hh.wx.xcx.eventBus.event.MaintainInfoPublicEvent;
import com.hh.wx.xcx.model.MaintainInfo;
import com.hh.wx.xcx.service.MaintainInfoService;
import com.hh.wx.xcx.service.mapper.MaintainInfoMapper;
import com.hh.wx.xcx.wx.eventbus.factory.DefaultDomainEventPublisher;

@Service
public class MaintainInfoServiceImpl implements MaintainInfoService {

	@Autowired
	private MaintainInfoMapper maintainInfoMapper;
	
	@Autowired
	private DefaultDomainEventPublisher defaultDomainEventPublisher;

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

	@Override
	public ResultVo<String> changeStatus(Long id) {
		Integer status = maintainInfoMapper.getMaintainInfoStatus(id);
		
		if(status < 2){
			
			//发布
			if(status == 0){
				maintainInfoMapper.changeStatus(id,status + 1);
				MaintainInfoPublicEvent event = new MaintainInfoPublicEvent();
				event.setMaintainInfoId(id);
				defaultDomainEventPublisher.postAsync(event);
			}
		}
		return ResultUtils.secusses();
	}

	@Override
	public MaintainInfo getById(Long maintainInfoId) {
		
		return maintainInfoMapper.getById(maintainInfoId);
	}

}
