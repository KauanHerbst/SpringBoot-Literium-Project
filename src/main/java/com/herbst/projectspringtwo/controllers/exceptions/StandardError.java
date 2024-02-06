package com.herbst.projectspringtwo.controllers.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StandardError {
    private String error;
    private Instant timestamp;
    private String message;
    private int Status;
    private String path;
}
