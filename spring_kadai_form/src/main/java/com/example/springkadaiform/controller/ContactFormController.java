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

    // 確認画面への遷移
    @PostMapping("/form")
    public String confirm(
            @ModelAttribute @Valid ContactForm contactForm,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            // バリデーションエラー → 元のフォーム画面へ戻す
            return "form";
        }

        // 問題なし → 確認画面へ
        model.addAttribute("contactForm", contactForm);
        return "confirm";
    }

    // 完了処理（今回はダミー）
    @PostMapping("/complete")
    public String complete() {
        return "completeView"; // 任意（今回はconfirm後に完了画面がある場合）
    }
}

