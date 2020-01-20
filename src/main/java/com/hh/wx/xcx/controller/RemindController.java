package com.hh.wx.xcx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hh.wx.xcx.commons.ResultVo;
import com.hh.wx.xcx.commons.model.Page;
import com.hh.wx.xcx.commons.model.PageQuery;
import com.hh.wx.xcx.model.Remind;
import com.hh.wx.xcx.service.RemindService;

@RestController
@RequestMapping("remian")
public class RemindController {
	
	@Autowired
	private RemindService remindService;
	

	@RequestMapping(value="add",method=RequestMethod.POST)
	public ResultVo<String> add(@RequestBody Remind remind){
		
		return remindService.add(remind);
	}
	
	@RequestMapping(value="topOrNo",method=RequestMethod.PUT)
	public ResultVo<String> topOrNo(Boolean top,Long remindId){
		
		return remindService.topOrNo(remindId,top);
	}
	
	@RequestMapping(value="publishOrNo",method=RequestMethod.PUT)
	public ResultVo<String> publishOrNo(Boolean publish,Long remindId){
		
		return remindService.publishOrNo(remindId,publish);
	}
	
	/**
	 * @param publish
	 * @param remindId
	 * @return
	 * @desc 管理端查询
	 */
	@RequestMapping(value="get/{remindId}",method=RequestMethod.GET)
	public ResultVo<Remind> get(Boolean publish,Long remindId){
		
		return remindService.get(remindId,true);
	}
	
	/**
	 * @param publish
	 * @param remindId
	 * @return
	 * @desc 客户端查询
	 */
	@RequestMapping(value="getEntry/{remindId}",method=RequestMethod.GET)
	public ResultVo<Remind> getEntry(Boolean publish,Long remindId){
		
		return remindService.get(remindId,false);
	}
	
	/**
	 * @param remind
	 * @param isPc 是否是管理端查询
	 * @return
	 */
	@RequestMapping(value="query",method=RequestMethod.POST)
	public ResultVo<Page<Remind>> query(@RequestBody PageQuery<Remind> query,Boolean isPc){
		if(isPc == null){
			isPc = true;
		}
		return remindService.query(query,isPc);
	}
	
	
	
}

