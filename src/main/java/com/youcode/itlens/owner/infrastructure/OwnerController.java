package com.youcode.itlens.owner.infrastructure;

import com.youcode.itlens.owner.application.dtos.OwnerRequestDTO;
import com.youcode.itlens.owner.application.dtos.OwnerResponseDTO;
import com.youcode.itlens.owner.application.services.OwnerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/owners")
@RequiredArgsConstructor
public class OwnerController {
    private final OwnerService ownerService;

    @PostMapping
    public ResponseEntity<OwnerResponseDTO> create(@Valid @RequestBody OwnerRequestDTO owner) {
        OwnerResponseDTO createdOwner = ownerService.save(owner);
        return new ResponseEntity<>(createdOwner, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OwnerResponseDTO> getById(@PathVariable Long id) {
        OwnerResponseDTO owner = ownerService.getById(id);
        if (owner != null) {
            return new ResponseEntity<>(owner, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<List<OwnerResponseDTO>> getAll() {
        List<OwnerResponseDTO> owners = ownerService.getAll();
        return new ResponseEntity<>(owners, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OwnerResponseDTO> update(@PathVariable Long id, @RequestBody OwnerRequestDTO owner) {
        OwnerResponseDTO updatedOwner = ownerService.update(id, owner);
        if (updatedOwner != null) {
            return new ResponseEntity<>(updatedOwner, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (ownerService.delete(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
