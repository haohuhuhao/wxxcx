package com.hh.wx.xcx.service.impl;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hh.wx.xcx.commons.IdGenerator;
import com.hh.wx.xcx.commons.LoginInfoUtils;
import com.hh.wx.xcx.commons.LoginUserInfo;
import com.hh.wx.xcx.commons.ResultUtils;
import com.hh.wx.xcx.commons.ResultVo;
import com.hh.wx.xcx.commons.WXLoginUserInfo;
import com.hh.wx.xcx.commons.model.Page;
import com.hh.wx.xcx.commons.model.PageQuery;
import com.hh.wx.xcx.model.Remind;
import com.hh.wx.xcx.service.RemindService;
import com.hh.wx.xcx.service.mapper.RemindMapper;

@Service
public class RemindServiceImpl implements RemindService {

	@Autowired
	private RemindMapper remindMapper;

	@Override
	public ResultVo<String> add(Remind remind) {
		LoginUserInfo loginUserInfo = LoginInfoUtils.getLoginInfo(LoginUserInfo.class);
		
		Long id = IdGenerator.getInstance().generateId();
		
		remind.setId(id);
		remind.setBusinessType(loginUserInfo.getBusinessType());
		remind.setAppId(loginUserInfo.getAppId());
		
		remindMapper.insert(remind);
		return ResultUtils.secusses();
	}

	@Override
	public ResultVo<String> topOrNo(Long remindId, Boolean top) {
		LoginUserInfo loginUserInfo = LoginInfoUtils.getLoginInfo(LoginUserInfo.class);
		
		Remind remind = new Remind();
		remind.setProperty((top != null && top)?1:0);
		remind.setAppId(loginUserInfo.getAppId());
		remind.setBusinessType(loginUserInfo.getBusinessType());
		remind.setId(remindId);
		remindMapper.topOrNo(remind);
		return ResultUtils.secusses();
	}

	@Override
	public ResultVo<String> publishOrNo(Long remindId, Boolean publish) {
		LoginUserInfo loginUserInfo = LoginInfoUtils.getLoginInfo(LoginUserInfo.class);
		
		Remind remind = new Remind();
		remind.setStatus((publish != null && publish)?1:0);
		remind.setAppId(loginUserInfo.getAppId());
		remind.setBusinessType(loginUserInfo.getBusinessType());
		remind.setId(remindId);
		remindMapper.publishOrNo(remind);
		return ResultUtils.secusses();
	}

	@Override
	public ResultVo<Remind> get(Long remindId,Boolean pcOrwx) {
		
		Remind remind = getEntry(remindId);
		Long appId = null;
		if(pcOrwx){
			LoginUserInfo loginUserInfo = LoginInfoUtils.getLoginInfo(LoginUserInfo.class);
			appId = loginUserInfo.getAppId();
		}else{
			WXLoginUserInfo userInfo = LoginInfoUtils.getLoginInfo(WXLoginUserInfo.class);
			appId = userInfo.getAppId();
		}
		if(remind.getAppId().equals(appId)){
			ResultUtils.secusses(remind);
		}
		return ResultUtils.secusses();
	}

	@Override
	public Remind getEntry(Long remindId) {
		return remindMapper.getById(remindId);
	}

	@Override
	public ResultVo<Page<Remind>> query(PageQuery<Remind> query, Boolean isPc) {
		
		Remind queryRemind = query.getData();
		if(queryRemind == null){
			queryRemind = new Remind();
			query.setData(queryRemind);
		}
		
		Long appId = null;
		String businessType = null;
		if(isPc){
			LoginUserInfo loginUserInfo = LoginInfoUtils.getLoginInfo(LoginUserInfo.class);
			appId = loginUserInfo.getAppId();
			businessType = loginUserInfo.getBusinessType();
		}else{
			WXLoginUserInfo userInfo = LoginInfoUtils.getLoginInfo(WXLoginUserInfo.class);
			appId = userInfo.getAppId();
			businessType = userInfo.getBusinessType();
			
			queryRemind.setStatus(1);
		}
		
		queryRemind.setAppId(appId);
		queryRemind.setBusinessType(businessType);
		
		Long count = remindMapper.count(queryRemind);
		
		List<Remind> reminds = remindMapper.list(queryRemind,(query.getPage()-1)*query.getPageSize(),query.getPageSize());
		
		return ResultUtils.secusses(new Page<Remind>(query,count,reminds));
	}

}
