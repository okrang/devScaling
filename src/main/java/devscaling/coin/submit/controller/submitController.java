package devscaling.coin.submit.controller;

import devscaling.coin.submit.entity.PersonalState;
import devscaling.coin.submit.entity.SubmitBoard;
import devscaling.coin.submit.service.SubmitBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/submit_board")
@RequiredArgsConstructor
public class submitController {
    private final SubmitBoardService submitBoardService;



    //id로 찾기
    @GetMapping("/{id}")
    public ResponseEntity<SubmitBoard> getSubmitBoardById(@PathVariable Long id){
        SubmitBoard submitBoard=submitBoardService.getSubmitBoardById(id);
        return new ResponseEntity<>(submitBoard, HttpStatus.OK);
    }

    //delete하기
    @DeleteMapping("/{id}")
    public void deleteSubmitBoard(@PathVariable Long id){
        submitBoardService.deleteSubmitBoard(id);

    }
    //personalstate로 찾기
    @GetMapping("/personal_state/{id}")
    public ResponseEntity<List<SubmitBoard>> getByPersonalStates(@PathVariable PersonalState personalState){
        List<SubmitBoard> submitBoardList=submitBoardService.findByPersonalState(personalState);
        return new ResponseEntity<>(submitBoardList,HttpStatus.OK);
    }

    //저장하기
//    @PostMapping
//    public ResponseEntity<SubmitBoard> createSubmitBoard(@RequestBody SubmitBoard submitBoard,
//                                                         @RequestParam Long personalStateId) {
//        // PersonalState 객체를 가져오기 위해 ID로 조회
//        PersonalState personalState = ...; // PersonalState를 조회하는 로직 필요
//        submitBoardService.createSubmitBoard(submitBoard, personalState);
//        return new ResponseEntity<>(submitBoard, HttpStatus.CREATED);
//    }

}
