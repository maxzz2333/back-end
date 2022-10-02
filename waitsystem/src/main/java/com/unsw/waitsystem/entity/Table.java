package com.unsw.waitsystem.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("tables")
public class Table {
    @TableId(type = IdType.AUTO)
    private Long tableId;

    private int tableState;

    public Table(int tableState) {
        this.tableState = tableState;
    }

    public Long getTableId() {
        return tableId;
    }

    public void setTableId(Long tableId) {
        this.tableId = tableId;
    }

    public int getTableState() {
        return tableState;
    }

    public void setTableState(int tableState) {
        this.tableState = tableState;
    }
}
