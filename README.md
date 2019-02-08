# Anonymous Evaluation System
The system consists of 2 web applications, one used for issuing a ticket for the student and the other one used to submit his evaluation of an instructor and his course.
## The issue application
In this application, the student has to authenticate himself in order to request a ticket for the evaluation of an instructor and a course.  

The ticket is the hash (SHA-256) of a string from 5 random words of a wordlist of around 6000 words in order to avoid collisions. Afterward, the ticket is blinded with a random number r and the public key of the server and is sent to the server to get signed.  
Finally, the blind signature gets unblinded and the student now has a valid signature from the server.

## The evaluate application
The usage of this application is to evaluate a particular instructor and his contribution to a specific course.
The student has to submit his form using the original message created from the wordlist and the signed ticket.  

Then, the application uses the public key of the server to verify the signature and generates the hash of the original message. If the generated hash is equal to the verification of the signed ticket, the form is submitted.

