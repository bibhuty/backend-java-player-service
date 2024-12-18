package com.app.playerservicejava.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GeneratedTeamResponse {
    private String seed_id;
    private String prediction_id;
    private String team_size;
    private List<String> member_ids;
}
