package com.hh.wx.xcx.service;

import java.util.List;

import com.hh.wx.xcx.commons.ResultVo;
import com.hh.wx.xcx.model.MaintainDetail;

public interface MaintainDetailService {

	ResultVo<String> create(MaintainDetail maintainDetail);

	ResultVo<List<MaintainDetail>> queryBymaintainInfoId(Long maintainInfoId);

	ResultVo<String> update(MaintainDetail maintainDetail);

	ResultVo<String> bacthCreate(List<MaintainDetail> maintainDetails);

	ResultVo<String> batchUpdate(List<MaintainDetail> maintainDetails);


}
