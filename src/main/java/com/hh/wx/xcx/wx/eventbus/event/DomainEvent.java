package com.hh.wx.xcx.wx.eventbus.event;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class DomainEvent implements Serializable {
	private Date createTime;
    /**
     * @Fields field : 事件id
     * @author mb.wang
     * @date 2018/5/29 20:29
     */
    private String eventId;
    /**
     * @Fields field : 事件类型。eg:Order.add
     * @author mb.wang
     * @date 2018/5/29 20:30
     */
    private String eventType;
    /**
     * @Fields field : 实体类型。eg:Order
     * @author mb.wang
     * @date 2018/5/29 20:31
     */
    private String entityType;
    /**
     * @Fields field : 实体id（数据库中的记录id）
     * @author mb.wang
     * @date 2018/5/29 20:32
     */
    @NonNull
    private String entityId;
}
