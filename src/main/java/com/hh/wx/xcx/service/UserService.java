package com.hh.wx.xcx.service;

import com.hh.wx.xcx.commons.ResultVo;
import com.hh.wx.xcx.model.User;

public interface UserService {

	ResultVo<String> insert(User user);

	ResultVo<String> loginByPhone(String nameOrPhone, String pwd);

	ResultVo<String> update(User user,String token);

	ResultVo<String> chooseAppId(Long appId,String token);

}
