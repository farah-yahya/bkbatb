package com.example.atb.Service.Impl;

import com.example.atb.Entities.Atb;
import com.example.atb.Repository.AtbRepository;
import com.example.atb.Service.AtbService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AtbServiceImpl implements AtbService {
    private final AtbRepository atbRepository;
    @Override
    public Atb addAtb(Atb atb) {
        return atbRepository.save(atb);
    }

    @Override
    public void deleteAtb(long id) {
        if (atbRepository.existsById(id)) {
            atbRepository.deleteById(id);
        }
    }

    @Override
    public Atb updateAtb(long id, Atb atb) {
        if (atbRepository.existsById(id)) {
            Atb atb1 = atbRepository.findById(id).get();
            atb1.setName(atb.getName());
            return atbRepository.save(atb1);
        }
        return null;
    }

    @Override
    public List<Atb> getAtbs() {
        return atbRepository.findAll();
    }

    @Override
    public Atb getAtbById(long id) {
        return atbRepository.findById(id).orElse(null);
    }
}
