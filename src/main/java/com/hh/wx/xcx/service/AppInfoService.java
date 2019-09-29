package com.hh.wx.xcx.service;

import java.util.List;

import com.hh.wx.xcx.commons.ResultVo;
import com.hh.wx.xcx.model.AppInfo;

public interface AppInfoService {

	ResultVo<String> add(AppInfo appInfo);

	ResultVo<List<AppInfo>> queryAll(AppInfo app);

	ResultVo<AppInfo> getById(Long id);

	ResultVo<String> update(AppInfo app);

	ResultVo<String> deleteById(Long id);

}
