package gr.unipi.lecturer.controller;

import gr.unipi.lecturer.dto.SignedChainDTO;
import gr.unipi.lecturer.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.math.BigInteger;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.List;

@RestController
public class SignController {

    @Autowired
    TicketService ticketService;

    @GetMapping("/signLectures")
    public String get(@RequestParam BigInteger courseId, @RequestParam BigInteger instructorId, @RequestParam BigInteger ticket){

        try {
            return ticketService.sign(courseId,instructorId,ticket).toString();
        }
        //TODO handle these exceptions
        catch (UnrecoverableKeyException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
