package com.thoughtworks.spring.jpa.tomcat.controllers;

import com.thoughtworks.spring.jpa.tomcat.entities.Picture;
import com.thoughtworks.spring.jpa.tomcat.services.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

/**
 * Created by qnxu on 1/27/15.
 */
@Controller
@RequestMapping("/picture")
public class PictureController {
    @Autowired
    private PictureService pictureService;

    @RequestMapping( method = RequestMethod.GET)
    public String showPicture(Model model){
        return "picture";
    }

    @RequestMapping(value = "/data", method = RequestMethod.GET)
    public @ResponseBody String getPictureInformation(@RequestParam int pictureId){
        Picture picture = pictureService.getPictureInformation(pictureId);
        return picture.getPictureName();
    }
}
