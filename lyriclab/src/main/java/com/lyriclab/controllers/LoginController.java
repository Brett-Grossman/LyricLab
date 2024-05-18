package com.lyriclab.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.lyriclab.models.Account;
import com.lyriclab.models.LoginUser;
import com.lyriclab.models.Lyric;
import com.lyriclab.services.AccountService;
import com.lyriclab.services.lyricService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class LoginController {
    

	@Autowired
	private AccountService accountService;
	@Autowired
	private lyricService lyricService;
    
    @GetMapping("/")
    public String main(Model model) {
    
        model.addAttribute("newAcc", new Account());
        model.addAttribute("newLogin", new LoginUser());
        return "LogRegForm";
    }
    
    @PostMapping("/register")
    public String register(Model model, HttpSession session,
    		@Valid @ModelAttribute("newAcc") Account newAccount,BindingResult result) {
        
        Account newAcc = accountService.register(newAccount, result);
        
        if (result.hasErrors()) {
            model.addAttribute("newLogin", new LoginUser());
            return "LogRegForm";
        }
        
        if (newAcc == null) {
            return "LogRegForm";
        }
        
        session.setAttribute("accountId",newAcc.getId());
    
        return "redirect:/home";

    }
   
    @GetMapping("/home")
    public String homepage(Model model, HttpSession session) {
        Long accountId = (Long) session.getAttribute("accountId");
        
        if (accountId == null) {
            return "redirect:/"; 
        }

        Account account = accountService.findById(accountId);
        if (account == null) {
            return "redirect:/";
        }

        model.addAttribute("acc", account);
        
        List<Lyric> lyrics = lyricService.all();
        model.addAttribute("Lyrics", lyrics);
        
        return "HomePage";
    }
   
    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin, 
            BindingResult result, Model model, HttpSession session) {

        Account authenticatedAcc = accountService.login(newLogin, result);

        if (result.hasErrors()) {
            model.addAttribute("newAcc", new Account());
            return "LogRegForm";
        }

        if (authenticatedAcc == null) {
            return "LogRegForm";
        }

        session.setAttribute("accountId", authenticatedAcc.getId());

        return "redirect:/home";
    }
    
    @GetMapping("/logout")
    public String logout(HttpSession session) {

        session.invalidate();
        return "redirect:/"; 
    }
    
    @GetMapping("/newSong")
    public String newSong(@ModelAttribute("Lyric") Lyric lyric, Model model, HttpSession session) {
        Long accountId = (Long) session.getAttribute("accountId");

        if (accountId == null) {
            return "redirect:/";
        }

        Account account = accountService.findById(accountId);
        if (account == null) {
            return "redirect:/";
        }
    	Account account1 = accountService.findById((Long)session.getAttribute("accountId"));
    	model.addAttribute("Account", account1);
    	model.addAttribute("lyric", new Lyric());
    	return "newSong";
    }
    @PostMapping("/Song")
    public String createPost(@Valid @ModelAttribute("lyric") Lyric lyric, BindingResult result, HttpSession session) {

        Long accountId = (Long) session.getAttribute("accountId");

        if (accountId == null) {
            return "redirect:/"; 
        }

        Account account = accountService.findById(accountId);
        if (account == null) {
            return "redirect:/"; 
        }
    	
    	if (result.hasErrors()) {
    		return "newSong";
    	}
    	Account account1 = accountService.findById((Long)session.getAttribute("accountId"));
    	
    	lyric.setAccounts(account1);
    	
    	lyricService.create(lyric);
    	
    	return "redirect:/home";
    }
    @GetMapping("/Song/{id}")
    public String viewSong(Model model, @PathVariable Long id, HttpSession session) {
        Long accountId = (Long) session.getAttribute("accountId");

        if (accountId == null) {
            return "redirect:/";
        }

        Account account = accountService.findById(accountId);
        if (account == null) {
            return "redirect:/";
        }
 
        Lyric lyric = lyricService.findById(id);
        if (lyric == null) {

            return "redirect:/home";
        }

        model.addAttribute("Lyric", lyric);
        model.addAttribute("Account", lyric.getAccounts());

        return "ViewPage";
    }
    @GetMapping("/editSong/{id}")
    public String editSong(Model model, @PathVariable Long id, HttpSession session) {
        if (session == null || session.getAttribute("accountId") == null) {
            return "redirect:/home";
        }

        Lyric lyric = lyricService.findById(id);

        if (lyric == null) {

            return "redirect:/home";
        }

        model.addAttribute("lyric", lyric);

        return "EditSong";
    }
    @PostMapping("/editSong/{id}")
    public String updateSong(@Valid @ModelAttribute("lyric") Lyric updatedSongPost,
            BindingResult result, @PathVariable Long id, HttpSession session) {
        Long accountId = (Long) session.getAttribute("accountId");

        if (accountId == null) {
            return "redirect:/";
        }

        Account account = accountService.findById(accountId);
        if (account == null) {
            return "redirect:/";
        }
    	
        Lyric originalSongPost = lyricService.findById(id);

        if (originalSongPost == null) {

            return "redirect:/home";
        }

        originalSongPost.setSong_name(updatedSongPost.getSong_name());
        originalSongPost.setGenre(updatedSongPost.getGenre());
        originalSongPost.setSongLyrics(updatedSongPost.getSongLyrics());

        if (result.hasErrors()) {
    		return "EditSong";
        }
                
        lyricService.create(originalSongPost);
        
        lyricService.update(updatedSongPost);
        

        return "redirect:/home";
    }
    @GetMapping("/deleteSong/{id}")
    public String deleteSong(@PathVariable Long id, HttpSession session) {
        Long accountId = (Long) session.getAttribute("accountId");

        if (accountId == null) {
            return "redirect:/";
        }

        Account account = accountService.findById(accountId);
        if (account == null) {
            return "redirect:/";
        }

        Lyric lyric = lyricService.findById(id);

        if (lyric != null && lyric.getAccounts().getId().equals(accountId)) {
            lyricService.delete(id);
        }

        return "redirect:/home";
    }
}
    
    
    
