package com.teentech.hotels.controller;

import com.teentech.hotels.dto.MenuItemForPageDto;
import com.teentech.hotels.service.MenuItemForPageService;
import com.teentech.hotels.util.GetEntryTypes;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("")
@Log4j2
public class PublicMenuController {

    @Autowired
    private MenuItemForPageService menuItemForPageService;

    @GetMapping("{hotelId}")
        public ModelAndView menuPage(Model model, @PathVariable int hotelId) {
            try {
                List<MenuItemForPageDto> menu = menuItemForPageService.getAllFromHotel(hotelId);
                List<String> entryTypes = GetEntryTypes.getEntryTypes(menu);
                model.addAttribute("menuItems", menu);
                model.addAttribute("entryTypes", entryTypes);

                ModelAndView modelAndView = new ModelAndView();
                modelAndView.setViewName("viewMenu");
                return modelAndView;
            } catch (Exception e) {
                log.error("Error while getting info for the page", e);
            }
            return null;
    }
}
