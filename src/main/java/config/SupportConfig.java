package config;

import annotations.Configuration;
import annotations.Instance;

@Configuration
public class SupportConfig {
    @Instance
    public SupportConfig supportConfig(){
        return new SupportConfig();
    }
}
