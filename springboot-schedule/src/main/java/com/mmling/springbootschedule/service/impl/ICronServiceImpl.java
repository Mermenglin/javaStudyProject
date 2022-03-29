package com.mmling.springbootschedule.service.impl;

import com.mmling.springbootschedule.entity.Cron;
import com.mmling.springbootschedule.service.ICronService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ICronServiceImpl implements ICronService {

    private volatile boolean status = false;

    @Override
    public boolean saveOrUpdate(Cron cron) {
        return true;
    }

    @Override
    public Cron getById(Integer cronId) {
        return null;
    }

    @Override
    public boolean removeById(Integer cronId) {
        return true;
    }

    @Override
    public List<Cron> list() {
        return null;
    }

    @Override
    public boolean getStatus() {
        return status;
    }

    @Override
    public void setStatus(boolean status) {
        log.info("修改状态为成功");
        this.status = status;
    }
}