package br.furb.suficiencia;

import jakarta.annotation.Nonnull;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponse;

public record ErrorResponseDTO () implements ErrorResponse  {

    @Override
    @Nonnull
    public HttpStatusCode getStatusCode() {
        return HttpStatusCode.valueOf(400);
    }

    @Override
    @Nonnull
    public ProblemDetail getBody() {
        return null;
    }

}
