package com.example.springkadaiform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.springkadaiform.form.ContactForm;

import jakarta.validation.Valid;

@Controller
public class ContactFormController {

    // フォーム画面の表示
    @GetMapping("/form")
    public String showForm(Model model) {
        model.addAttribute("contactForm", new ContactForm());
        return "form";
    }

    // 確認画面に進む（POST）
    @PostMapping("/form")
    public String confirm(
            @ModelAttribute("contactForm") @Valid ContactForm contactForm,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            // エラーがあればフォームに戻る
            return "form";
        }

        // contactForm は自動で Model に入る
        return "confirm";
    }

    // 完了画面
    @PostMapping("/complete")
    public String complete() {
        return "complete";
    }

    // ★ GET /confirm に直接アクセスされた場合の防御
    @GetMapping("/confirm")
    public String redirectConfirm() {
        return "redirect:/form";
    }
}



