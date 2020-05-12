package controller;



import dto.PersonDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping(path = "/v1/people")
@Validated
public class PersonController {

    private PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public ResponseEntity<PersonDto> create(@RequestBody @Valid PersonDto personDTO) {
        PersonDto personCreate = personService.create(personDTO);
        return new ResponseEntity<PersonDto>(personCreate, null, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<PersonDto> update(@RequestBody @Valid PersonDto personDTO) {
        return ResponseEntity.ok(personService.update(personDTO));
    }

    @GetMapping
    public ResponseEntity<List<PersonDto>> findAll() {
        return ResponseEntity.ok(personService.findAll());
    }

    @Validated
    @GetMapping("/{id}")
    public ResponseEntity<PersonDto> findById(
            @PathVariable @NotNull @Valid Long id) {
        return ResponseEntity.ok(personService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        personService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
