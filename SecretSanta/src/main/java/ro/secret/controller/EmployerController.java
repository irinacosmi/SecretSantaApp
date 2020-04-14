package ro.secret.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.sun.xml.bind.v2.runtime.output.SAXOutput;
import org.hibernate.boot.archive.scan.spi.ScanOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import ro.secret.entity.Employer;
import ro.secret.exception.EmployerExtractException;
import ro.secret.services.EmployerService;
import ro.secret.services.ExtractService;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class EmployerController {
    private static String UPLOADED_FOLDER = "E:/Irina/SecretSanta/src/main/resources/static/upload/";
    private Map<String, String> errorMessage = new HashMap<>();

    @Autowired
    private EmployerService employerService;

    @Autowired
    private ExtractService extractService;

    @RequestMapping("/")
    public String getAll(Model model) {
        List<Employer> employers = employerService.getAll();
        model.addAttribute("message", errorMessage.getOrDefault("message", null));
        model.addAttribute("employers", employers);
        return "employer";
    }

    @PostMapping("/add")
    public String addNew(@RequestParam("name") String name, @RequestParam("wish") String wish,
                         @RequestParam("details") String details, @RequestParam("image") MultipartFile image)
    {
        String uploadFileName = image.getOriginalFilename();
        String fileExtension = uploadFileName.substring(uploadFileName.lastIndexOf(".") + 1);
        String fileName = name + "." + fileExtension;
        Employer employer = new Employer();
        employer.setName(name);
        employer.setWish(wish);
        employer.setDetails(details);
        employer.setImage(fileName);
        employer.setExtract(false);
        employerService.addNew(employer);
        try {
            byte[] bytes = image.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + fileName);
            Files.write(path, bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/";
     }

    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable("id") int id, Model model) {
        Employer employer = employerService.getById(id);
        model.addAttribute("Employer", employer);
        model.addAttribute("absoluteURL", UPLOADED_FOLDER);
        return "update";
    }

    @RequestMapping("/extract/{id}")
    public String  extract(@PathVariable("id") int id)   {
        errorMessage.remove("message");
        try{
            extractService.extract(id);
        }
        catch (EmployerExtractException e){
            System.out.println(e.getMessage());
            errorMessage.put("message", e.getMessage());
       }
        return "redirect:/";
    }

    @PostMapping("/update")
    public String update(@Valid Employer employer,  @RequestParam(value="action") String action) {
        if (action.equals("update")) {
            employerService.save(employer);
        }

        if (action.equals("delete")) {
            employerService.delete(employer);
            errorMessage.clear();
        }
        return "redirect:/";
    }
}


