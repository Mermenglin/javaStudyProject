package com.mmling.springbootschedule.service;

import com.mmling.springbootschedule.entity.Cron;

import java.util.List;

public interface ICronService {
    boolean saveOrUpdate(Cron cron);

    Cron getById(Integer cronId);

    boolean removeById(Integer cronId);

    List<Cron> list();

    boolean getStatus();

    void setStatus(boolean status);
}