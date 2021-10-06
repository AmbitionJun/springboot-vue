package com.ambitionjun.movie.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin {
    private Long admin_id;
    private String admin_name;
    private String admin_password;
}
