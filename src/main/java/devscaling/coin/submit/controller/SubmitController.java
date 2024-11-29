package devscaling.coin.submit.controller;

import devscaling.coin.submit.DTO.*;
import devscaling.coin.submit.entity.PersonalState;
import devscaling.coin.submit.entity.SubmitBoard;
import devscaling.coin.submit.service.SubmitBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;


import java.util.List;

@Controller
@RequestMapping("/submit_board")
@RequiredArgsConstructor
public class SubmitController {
    private final SubmitBoardService submitBoardService;

    @PostMapping
    public String createSubmitBoard(SubmitBoardDto dto,Model model){
        dto.setMember("TEST_USER");
        submitBoardService.createSubmitBoard(dto);
        return "redirect:/home";

    }

    //id로 찾기
    @GetMapping("/board/{id}")
    public String getSubmitBoardById(@PathVariable Long id,Model model){
        SubmitBoardResponseDto submitBoardResponseDto=submitBoardService.getSubmitBoardById(id);
        model.addAttribute("submitBoard",submitBoardResponseDto);
        return "board";
    }

    //delete하기
//    @DeleteMapping("/{id}")
//    public void deleteSubmitBoard(@PathVariable Long id){
//        submitBoardService.deleteSubmitBoard(id);
//
//    }
    //personalstate로 찾기




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
