package com.battlejawn.Config;

import java.net.URI;
import lombok.Data;

@Data
public class UserResponse {
    private URI location;
    private long id;

    public UserResponse(URI location, long id) {
        this.location = location;
        this.id = id;
    }
    
}
