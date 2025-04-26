package com.example.My_Converter_project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/history")
@CrossOrigin(origins = "*")//allow requests from other sources
public class ConversionHistoryController {
    @Autowired
    private ConversionHistoryRepository conversionHistoryRepository;
    @GetMapping //get history conversion
    public ResponseEntity<List<ConversionHistory>> getHistory(){
        List<ConversionHistory> history = conversionHistoryRepository.findAll();
            return ResponseEntity.ok(history);
        }
    //get history conversion of user
    @GetMapping("/{userId}") //get history conversion
    public ResponseEntity <List<ConversionHistoryDTO>> getConversionHistoryByUserId(@PathVariable Long userId){
        List<ConversionHistory> historyList = conversionHistoryRepository.findByUserId( userId);
        List<ConversionHistoryDTO> dtoList = historyList.stream()
                .map(history -> new ConversionHistoryDTO(
                      history.getConversionId(),
                      history.getSourceCurrency(),
                      history.getTargetCurrency(),
                      history.getSourceAmount(),
                      history.getTargetAmount(),
                      history.getOperationDate()

                ))
                .collect(Collectors.toList());
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }
    @DeleteMapping("/clear") //get history conversion
    public ResponseEntity<String>clearHistory(){
         conversionHistoryRepository.deleteAll();
         return ResponseEntity.ok("{\"message\":\"History cleared\"}");
    }

}




