package com.idalavye.petclinic.web;

import com.idalavye.petclinic.exception.InternalServerException;
import com.idalavye.petclinic.exception.OwnerNotFoundException;
import com.idalavye.petclinic.model.Owner;
import com.idalavye.petclinic.service.PetClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/rest")
public class PetClinicRestController {

    @Autowired
    private PetClinicService petClinicService;

    @RequestMapping(method = RequestMethod.POST,value = "/owner")
    public ResponseEntity<URI> createOwner(@RequestBody Owner owner){
        try {
            petClinicService.createOwner(owner);
            Long id = owner.getId();
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
            return ResponseEntity.created(location).build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @RequestMapping(method = RequestMethod.PUT,value = "/owner/{id}")
    public ResponseEntity<?> updateOwner(@PathVariable("id") Long id,@RequestBody Owner owner){
        try {
            Owner owner1 = petClinicService.findOwner(id);
            owner1.setFirstName(owner.getFirstName());
            owner1.setLastName(owner.getLastName());
            petClinicService.updateOwner(owner1);
            return ResponseEntity.ok().build();
        }catch (OwnerNotFoundException e){
            return ResponseEntity.notFound().build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @RequestMapping(value = "/owners",method = RequestMethod.GET)
    public ResponseEntity<List<Owner>> getOwners(){
        List<Owner> owners = petClinicService.findOwners();
        return ResponseEntity.ok(owners);
    }

    @RequestMapping(value = "/owner",method = RequestMethod.GET)
    public ResponseEntity<List<Owner>> getOwners(@RequestParam("ln") String lastName){
        List<Owner> owners = petClinicService.findOwners(lastName);
        return ResponseEntity.ok(owners);
    }

    @RequestMapping(value = "/owner/{id}",method = RequestMethod.GET)
    public ResponseEntity<Owner> getOwner(@PathVariable("id") Long id){
        try {
            Owner owner = petClinicService.findOwner(id);
            return ResponseEntity.ok(owner);
        }catch (OwnerNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(value = "/owner/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteOwner(@PathVariable("id") Long id){
        try {
            Owner owner = petClinicService.findOwner(id);
            petClinicService.deleteOwner(owner.getId());
            return ResponseEntity.ok().build();
        }catch (OwnerNotFoundException e){
            throw e;
        }catch (Exception e){
            throw new InternalServerException(e);
        }
    }

    //HATEOAS Sample
    //produces attribute undaki tanım ile aslında diğer getOwner methodu ile aynı request url sahip olsa bile bu method istemci json formatında bir yanıt beklediğinde
    //normal getOwner metodu değilde aşarıdaki method invoke edilecektir.
    @RequestMapping(value = "/owner/{id}",method = RequestMethod.GET,produces = "application/json")
    public ResponseEntity<?> getOwnerAsHateoasResource(@PathVariable("id") Long id){
        try {
            Owner owner = petClinicService.findOwner(id);
            Link self = ControllerLinkBuilder.linkTo(PetClinicRestController.class).slash("/owner/" + id).withSelfRel();
            Link create = ControllerLinkBuilder.linkTo(PetClinicRestController.class).slash("/owner").withRel("create");
            Link update = ControllerLinkBuilder.linkTo(PetClinicRestController.class).slash("/owner/" + id).withRel("update");
            Link delete = ControllerLinkBuilder.linkTo(PetClinicRestController.class).slash("/owner/" + id).withRel("delete");

            Resource<Owner> resource = new Resource<>(owner,self,create,update,delete);
            return ResponseEntity.ok(resource);
        }catch (OwnerNotFoundException e){
            return ResponseEntity.notFound().build();
        }catch (Exception e){
            throw new InternalServerException(e);
        }
    }
}
