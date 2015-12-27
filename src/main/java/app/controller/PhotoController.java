/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controller;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import app.domain.FileObject;
import app.repository.GifRepository;

/**
 *
 * @author Nanofus
 */
@Controller
@RequestMapping("photocan")
public class PhotoController {

    @Autowired
    private GifRepository gifRepository;

    @RequestMapping(method = RequestMethod.GET)
    public String vege() {
        return "redirect:/photocan/1";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String vege2(Model model, @PathVariable Long id) {

        model.addAttribute("count", gifRepository.count());

        if (id >= 0 && id < gifRepository.count()) {
            model.addAttribute("next", id + 1);
        }
        if (gifRepository.findOne(id - 1) != null) {
            model.addAttribute("previous", id - 1);
        }
        if (id >= 0 && id <= gifRepository.count()) {
            model.addAttribute("current", id);
        }

        return "photocan";
    }

    @RequestMapping(value = "/{id}/content", method = RequestMethod.GET, produces = "image/gif")
    @ResponseBody
    public byte[] vege3(@PathVariable Long id) {
        return gifRepository.findOne(id).getContent();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String save(@RequestParam("file") MultipartFile file) throws IOException {

        System.out.println("Saving image");
        System.out.println("Saving image");
        System.out.println("Saving image");
        System.out.println("Saving image");
        System.out.println("Saving image");
        System.out.println("Saving image");
        System.out.println("Saving image");
        System.out.println("Saving image");
        System.out.println("Saving image");
        System.out.println("Saving image");
        System.out.println("Saving image");
        System.out.println("Saving image");
        System.out.println("Saving image");
        System.out.println("Saving image");
        System.out.println("Saving image");
        System.out.println("Saving image");
        System.out.println("Saving image");
        System.out.println("Saving image");
        System.out.println("Saving image");
        System.out.println("Saving image");

        /*if (file.getContentType() != "image/png") {
            System.out.println("WRONG FORMAT!");
            System.out.println("WRONG FORMAT!");
            System.out.println("WRONG FORMAT!");
            System.out.println("WRONG FORMAT!");
            System.out.println("WRONG FORMAT!");
            System.out.println("WRONG FORMAT!");
            return "redirect:/gifs";
        }*/

        System.out.println("Image saved!");

        FileObject fo = new FileObject();
        fo.setContent(file.getBytes());

        gifRepository.save(fo);

        return "redirect:/photocan";
    }

}
