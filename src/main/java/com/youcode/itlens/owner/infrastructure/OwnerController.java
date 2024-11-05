package com.youcode.itlens.owner.infrastructure;

import com.youcode.itlens.owner.application.dtos.OwnerRequestDTO;
import com.youcode.itlens.owner.application.dtos.OwnerResponseDTO;
import com.youcode.itlens.owner.application.services.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/owners")
@RequiredArgsConstructor
public class OwnerController {

    private final OwnerService ownerService;
    @PostMapping
    public ResponseEntity<OwnerResponseDTO> create(@RequestBody OwnerRequestDTO owner) {
        OwnerResponseDTO createdOwner = ownerService.save(owner);
        return new ResponseEntity<>(createdOwner, HttpStatus.CREATED);
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<Owner> getById(@PathVariable Long id) {
//        Owner owner = ownerService.getById(id);
//        if (owner != null) {
//            return new ResponseEntity<>(owner, HttpStatus.OK);
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
//
    @GetMapping
    public ResponseEntity<List<OwnerResponseDTO>> getAll() {
        List<OwnerResponseDTO> owners = ownerService.getAll();
        return new ResponseEntity<>(owners, HttpStatus.OK);
    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<Owner> update(@PathVariable Long id, @RequestBody Owner owner) {
//        Owner updatedOwner = ownerService.update(id, owner);
//        if (updatedOwner != null) {
//            return new ResponseEntity<>(updatedOwner, HttpStatus.OK);
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> delete(@PathVariable Long id) {
//        if (ownerService.delete(id)) {
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }

}
