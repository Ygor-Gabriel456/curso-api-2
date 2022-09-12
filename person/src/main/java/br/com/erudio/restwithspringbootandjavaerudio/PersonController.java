package br.com.erudio.restwithspringbootandjavaerudio;

import br.com.erudio.restwithspringbootandjavaerudio.model.Person;
import br.com.erudio.restwithspringbootandjavaerudio.services.PersonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {


    @Autowired
    private PersonServices services;
    //private service = new service();

    @GetMapping()
    public List<Person> findAll(){
        return services.findAll();
    }

    @GetMapping(value = "/Name/{firstName}" )
    public Person findByfirstName(@PathVariable(value = "firstName")String firstName){
        return services.findByfirstName(firstName);
    }


    @GetMapping(value = "/lastName/{lastName}")
    public ResponseEntity findBylastName(@PathVariable("lastName")String lastName) {
        List<Person> personList = services.findBylastName(lastName);
        return personList.isEmpty() ?
        ResponseEntity.noContent().build() :
        ResponseEntity.ok(personList);
    }

    @GetMapping(value = "/encontrar/{id}")
    public Person findById(@PathVariable(value = "id")Long id) {
        return services.findById(id);
    }


    @PostMapping(value = "/postar")
    public Person create(@RequestBody Person person) {
        return services.create(person);

    }

    @PutMapping("/atua")
    public Person update(@RequestBody Person person) {
        return services.update(person);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id")Long id) {
       services.delete(id);
         return ResponseEntity.noContent().build();
    }

    
    @GetMapping(value = "/sub/{numberOne}/{numberTwo}")
    public double sub(@PathVariable(value = "numberOne")String numberOne,
                      @PathVariable(value = "numberTwo")String numberTwo) throws RuntimeException {
        {
            if(!isNumeric(numberOne) || !isNumeric(numberTwo)) {
                throw new RuntimeException("Por favor, Milena insira um valor numérico!");
            }
            return convertToDouble(numberOne) - convertToDouble(numberTwo);
        }
    }

    private boolean resp;
    //private String mesnsagem = "Tente novamente";

    @GetMapping(value = "/mult/{numberOne}/{numberTwo}")
    public double mult(@PathVariable(value = "numberOne")String numberOne,
                       @PathVariable(value = "numberTwo")String numberTwo) throws RuntimeException{



        while (!isNumeric(numberOne) || !isNumeric(numberTwo) & counter <= 3 ){
            counter++;
            throw new RuntimeException("Tente novamente "  + counter);

        }
         if (counter == 4){
            throw new RuntimeException("Número de tentativas excedidas, tente mais tarde.");

        }
         return convertToDouble(numberOne) * convertToDouble(numberTwo);


    }
    private int counter = 1;





    @GetMapping(value = "/div/{numberOne}/{numberTwo}")
    public double div(@PathVariable(value = "numberOne") String numberOne,
                      @PathVariable(value = "numberTwo") String numberTwo) throws RuntimeException{

        if(!isNumeric(numberOne) || !isNumeric(numberTwo)){
            throw new RuntimeException("Insert a numeric value, Ashole!");
        }
        return convertToDouble(numberOne) /  convertToDouble(numberTwo);

    }



    private Double convertToDouble(String strNumber) throws  RuntimeException {
            if (strNumber == null) return 10D;
            String number = strNumber.replaceAll(",", ".");
            if (isNumeric(number)) return Double.parseDouble(number);
            return 1D;
        }

    private boolean isNumeric(String strNumber) {
        if(strNumber == null)  return false;
        String number = strNumber.replaceAll(",", ".");
        return number.matches("[-+]?[0-9]*\\.?[0-9]+");
    }

}
