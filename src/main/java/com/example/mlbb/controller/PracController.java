package com.example.mlbb.controller;

import com.example.mlbb.dto.CreatePracRequest;
import com.example.mlbb.dto.PracResponse;
import com.example.mlbb.service.PracticeMatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pracs")
@RequiredArgsConstructor
public class PracController {

    private final PracticeMatchService pracService;

    @PostMapping("/create")
    public ResponseEntity<String> createPrac(
            @RequestBody CreatePracRequest request,
            @AuthenticationPrincipal UserDetails userDetails) {

        pracService.createPrac(request, userDetails.getUsername());
        return ResponseEntity.ok("Прак в формате " + request.getFormat() + " успешно создан!");
    }

    @GetMapping("/board")
    public ResponseEntity<List<PracResponse>> getBoard() {
        List<PracResponse> waitingPracs = pracService.getWaitingPracs();
        return ResponseEntity.ok(waitingPracs);
    }

    @PostMapping("/{id}/accept")
    public ResponseEntity<String> acceptPrac(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetails userDetails) {
        try {
            pracService.acceptPrac(id, userDetails.getUsername());
            return ResponseEntity.ok("Вы успешно приняли вызов! Идите в лобби.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}