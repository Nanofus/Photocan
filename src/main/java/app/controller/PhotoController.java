/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controller;

import app.domain.Comment;
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
import app.repository.CommentRepository;
import app.repository.ImageRepository;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Nanofus
 */
@Controller
@RequestMapping("photocan")
public class PhotoController {

    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private CommentRepository commentRepository;

    @RequestMapping(method = RequestMethod.GET)
    public String get(Model model) {
        model.addAttribute("count", imageRepository.count());
        model.addAttribute("images", imageRepository.findAll());
        return "photocan";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getId(Model model, @PathVariable Long id) {

        model.addAttribute("count", imageRepository.count());
        model.addAttribute("comments", commentRepository.findByImageId(id));

        if (id >= 0 && id < imageRepository.count()) {
            model.addAttribute("next", id + 1);
        }
        if (imageRepository.findOne(id - 1) != null) {
            model.addAttribute("previous", id - 1);
        }
        if (id >= 0 && id <= imageRepository.count()) {
            model.addAttribute("current", id);
        }

        return "image";
    }

    @RequestMapping(value = "/{id}/content", method = RequestMethod.GET, produces = "image/gif")
    @ResponseBody
    public byte[] getImage(@PathVariable Long id) {
        return imageRepository.findOne(id).getContent();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String saveImage(@RequestParam("file") MultipartFile file) throws IOException {
        FileObject fo = new FileObject();
        fo.setContent(file.getBytes());

        imageRepository.save(fo);

        return "redirect:/photocan";
    }

    @RequestMapping(value = "/{id}/comment", method = RequestMethod.POST)
    public String addComment(@PathVariable long id, @RequestParam("author") String author, @RequestParam("content") String content) {
        Comment comment = new Comment();
        comment.setAuthor(author);
        comment.setContent(content);
        comment.setCreated(new Date());
        comment.setImageId(id);

        commentRepository.save(comment);

        return "redirect:/photocan/{id}/";
    }
}
