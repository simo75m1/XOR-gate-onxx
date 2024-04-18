package com.example.gatedemo;

import ai.onnxruntime.OrtException;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.xml.crypto.Data;

@Controller
public class GateController
{
    DataHandler datahandler;

    public GateController (DataHandler datahandler){
        this.datahandler = datahandler;
}

    @GetMapping("")
    public String getFrontPage() {
        return "index";
    }

    @GetMapping("/xorgate")
    public String getXORGatePage(HttpSession session) {
        return "xorgatepage";
    }
    @PostMapping("/sendxor")
    public String xorGateData(@RequestParam int firstNumber, @RequestParam int secondNumber, HttpSession session) throws OrtException {
        double result = datahandler.getResultFromXOR(firstNumber, secondNumber);
        session.setAttribute("xorresult", result);
        return "redirect:/xorgate";
    }

}
