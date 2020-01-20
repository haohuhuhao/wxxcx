package com.hh.wx.xcx.service;

import com.hh.wx.xcx.commons.ResultVo;
import com.hh.wx.xcx.commons.model.Page;
import com.hh.wx.xcx.commons.model.PageQuery;
import com.hh.wx.xcx.model.Remind;

public interface RemindService {

	ResultVo<String> add(Remind remind);

	ResultVo<String> topOrNo(Long remindId, Boolean top);

	ResultVo<String> publishOrNo(Long remindId, Boolean publish);

	ResultVo<Remind> get(Long remindId,Boolean pcOrwx);
	
	Remind getEntry(Long remindId);

	ResultVo<Page<Remind>> query(PageQuery<Remind> query, Boolean isPc);

}
