package org.example;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SpaRedirectController {

    // Leitet alle /app/...-Pfadaufrufe an app/index.html weiter, solange sie kein . enthalten
    @RequestMapping("/app/{path:^(?!static|favicon\\.ico|manifest\\.json).*$}")
    public String forwardToApp() {
        return "forward:/app/index.html";
    }
}
