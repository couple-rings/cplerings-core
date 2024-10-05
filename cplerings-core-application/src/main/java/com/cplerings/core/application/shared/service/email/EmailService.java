package com.cplerings.core.application.shared.service.email;

import com.cplerings.core.common.dto.EmailInfo;

public interface EmailService {

    void sendMail(EmailInfo emailInfo);
}
