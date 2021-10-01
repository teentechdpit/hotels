package com.teentech.hotels.service;

import com.teentech.hotels.model.ItemType;
import com.teentech.hotels.repository.ItemTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemTypeService {

    @Autowired
    private ItemTypeRepository itemTypeRepository;

    public void add(ItemType itemType) { itemTypeRepository.save(itemType); }


}
