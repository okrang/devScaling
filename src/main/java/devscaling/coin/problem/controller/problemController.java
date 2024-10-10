package devscaling.coin.problem.controller;

import devscaling.coin.problem.entity.ProblemInfo;
import devscaling.coin.problem.service.ProblemInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/problem_info")
@RequiredArgsConstructor
public class problemController {
    private final ProblemInfoService probleminfoservice;

    @GetMapping("/{id}")
    public ResponseEntity<ProblemInfo> getProblemById(@PathVariable Long id) {
        ProblemInfo problemInfo = probleminfoservice.getProblemById(id);
        return new ResponseEntity<>(problemInfo, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProblemInfo> createProblem(@RequestBody ProblemInfo problemInfo) {
        // ProblemInfoService의 save 메소드를 호출하여 문제를 생성
        ProblemInfo createdProblem = probleminfoservice.createProblem(problemInfo);
        return new ResponseEntity<>(createdProblem, HttpStatus.CREATED);
    }
    //백준 API
}
