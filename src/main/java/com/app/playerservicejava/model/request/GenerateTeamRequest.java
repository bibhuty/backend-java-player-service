package com.app.playerservicejava.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenerateTeamRequest {
    private String seed_id;
    private int team_size;
}
