package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageService {

    @Autowired
    BlogRepository blogRepository2;
    @Autowired
    ImageRepository imageRepository2;

    public Image addImage(Integer blogId, String description, String dimensions) {

        Blog blog = blogRepository2.findById(blogId).get();
        Image image = new Image(blog,description,dimensions);
        blog.getImageList().add(image);
        blogRepository2.save(blog);
        return image;
    }

    public void deleteImage(Integer id){
        imageRepository2.deleteById(id);
    }

    public int countImagesInScreen(Integer id, String screenDimensions) {

        String [] scrarray = screenDimensions.split("X");
        Image image = imageRepository2.findById(id).get();

        String imageDimensions = image.getDimensions();
        String [] imgarray = imageDimensions.split("X");

        int scr_l = Integer.parseInt(scrarray[0]); //A -- > integer
        int scr_b = Integer.parseInt(scrarray[1]); //B -- > integer

        int img_l = Integer.parseInt(imgarray[0]); //A -- > integer
        int img_b = Integer.parseInt(imgarray[1]); //B -- > integer

        return no_Images(scr_l, scr_b, img_l, img_b);

    }

    private int no_Images(int scrl, int scrb, int imgl, int imgb) {
        int lenC = scrl/imgl;
        int lenB = scrb/imgb;
        return lenC*lenB;
    }
}