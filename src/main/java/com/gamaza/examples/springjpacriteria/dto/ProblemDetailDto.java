package com.gamaza.examples.springjpacriteria.dto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

import java.io.Serial;
import java.io.Serializable;
import java.net.URI;

/**
 * Problem Detail custom class
 */
public class ProblemDetailDto extends ProblemDetail implements Serializable {

    @Serial
    private static final long serialVersionUID = -1603671414622827522L;

    /**
     * Builder
     */
    public static class ProblemDetailDtoBuilder {

        // Response
        private final ProblemDetailDto problemDetailDto;

        private ProblemDetailDtoBuilder(HttpStatus status, String detail) {
            this.problemDetailDto = new ProblemDetailDto();
            this.problemDetailDto.setStatus(status.value());
            this.problemDetailDto.setDetail(detail);
        }

        public static ProblemDetailDtoBuilder newInstance(HttpStatus status, String detail) {
            return new ProblemDetailDtoBuilder(status, detail);
        }

        public ProblemDetailDtoBuilder withType(URI type) {
            this.problemDetailDto.setType(type);
            return this;
        }

        public ProblemDetailDtoBuilder withTitle(String title) {
            this.problemDetailDto.setTitle(title);
            return this;
        }

        public ProblemDetailDtoBuilder withProperty(String key, Object value) {
            this.problemDetailDto.setProperty(key, value);
            return this;
        }

        public ProblemDetailDto build() {
            return this.problemDetailDto;
        }

    }

}
