package com.example.atb.Service;

import com.example.atb.Entities.Atb;

import java.util.List;

public interface AtbService {
    Atb addAtb(Atb atb);
    void deleteAtb(long id);
    Atb updateAtb(long id, Atb atb);
    List<Atb> getAtbs();
    Atb getAtbById(long id);
}
