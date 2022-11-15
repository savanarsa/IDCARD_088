/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IDCARD.IDCARD;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author HP
 */
@Controller
public class controller {
    
    @RequestMapping("/IDCard")
    //@ResponseBody
    public String getCard(@RequestParam("namaku") String getText,
                          @RequestParam("tgllhr") @DateTimeFormat (pattern="yyyy-MM-dd") Date myDate,
                          @RequestParam("foto") MultipartFile image,
                          Model model) throws IOException{
        
        String blob = Base64.encodeBase64String(image.getBytes());
        String shImage = "data:image/jpeg;base64,".concat(blob);
        
        SimpleDateFormat tgl = new SimpleDateFormat("EEEE,dd MMMM yyyy");
        String tanggal = tgl.format(myDate);
        
        model.addAttribute("knm", getText);
        model.addAttribute("kimg", shImage);
        model.addAttribute("ktgl", tanggal);
        return "main";
    }
}