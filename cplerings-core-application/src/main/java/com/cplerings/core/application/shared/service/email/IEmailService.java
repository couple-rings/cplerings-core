package com.cplerings.core.application.shared.service.email;

import com.cplerings.core.common.dto.EmailDTO;

public interface IEmailService {

    void sendMail(EmailDTO emailDTO);
}
