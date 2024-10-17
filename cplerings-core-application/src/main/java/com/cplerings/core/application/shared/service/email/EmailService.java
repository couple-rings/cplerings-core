package com.cplerings.core.application.shared.service.email;

import com.cplerings.core.application.shared.service.CpleringsService;

@CpleringsService(
        code = "SV-003",
        description = "Service for email"
)
public interface EmailService {

    void sendMail(EmailInfo emailInfo);
}
