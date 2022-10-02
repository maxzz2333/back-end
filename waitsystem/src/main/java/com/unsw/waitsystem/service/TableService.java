package com.unsw.waitsystem.service;

import com.unsw.waitsystem.entity.Table;
import com.unsw.waitsystem.mapper.TableMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("tableService")
public class TableService{

    @Autowired
    @Qualifier("tableMapper")
    private TableMapper tableMapper;

    public List<Table> getAllTables() {
        return tableMapper.getAllTables();
    }

    public void selectTable(int tableId ) {
        tableMapper.selectTable(tableId);
    }

    public void leaveTable(int tableId ) {
        tableMapper.leaveTable(tableId);
    }
}
