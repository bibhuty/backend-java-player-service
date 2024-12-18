package com.app.playerservicejava.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TalkToLLM {
    private String talkIdentifier;
    private String talkContent;
}
