package com.idalavye.petclinic.web;

import com.idalavye.petclinic.model.Owner;
import com.idalavye.petclinic.service.PetClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class PetClinicEditOwnerController {

    @Autowired
    private PetClinicService petClinicService;

    @RequestMapping(value = "/owners/update/{id}",method = RequestMethod.GET)
    public String loadOwner(@PathVariable Long id, ModelMap modelMap){
        Owner owner = petClinicService.findOwner(id);
        modelMap.put("owner",owner);
        return "editOwner";
    }

    @RequestMapping(value = "/owners/update/{id}",method = RequestMethod.POST)
    public String handFormSubmit(@ModelAttribute @Valid Owner owner, BindingResult bindingResult, RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()){
            return "editOwner";
        }

        redirectAttributes.addFlashAttribute("message","Owner updated by id : " + owner.getId());
        petClinicService.updateOwner(owner);
        return "redirect:/owners";
    }

}
